package com.java.hunters.web.rest;

import com.java.hunters.domain.Competition;
import com.java.hunters.service.CompetitionService;
import com.java.hunters.web.vm.CompetitionVM;
import com.java.hunters.web.vm.mappers.CompetitionMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/competitions")
public class CompetitionController {

    private final CompetitionService competitionService;
    private final CompetitionMapper competitionMapper;

    public CompetitionController(CompetitionService competitionService, CompetitionMapper competitionMapper) {
        this.competitionService = competitionService;
        this.competitionMapper = competitionMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCompetition(@RequestBody @Valid CompetitionVM competitionVM) {
        Competition competition = competitionMapper.toEntity(competitionVM);
        competitionService.createCompetition(competition);

        return ResponseEntity.ok("Competition created successfully");
    }
}
