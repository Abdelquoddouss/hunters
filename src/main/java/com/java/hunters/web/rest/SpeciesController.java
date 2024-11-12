package com.java.hunters.web.rest;

import com.java.hunters.domain.Species;
import com.java.hunters.domain.enums.Difficulty;
import com.java.hunters.domain.enums.SpeciesType;
import com.java.hunters.service.SpeciesService;
import com.java.hunters.web.vm.SpeciesVM;
import com.java.hunters.web.vm.mappers.SpeciesCreateVmMappers;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/species")
@AllArgsConstructor
public class SpeciesController {

    private final SpeciesService speciesService;
    private final SpeciesCreateVmMappers speciesCreateVmMappers;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody @Valid SpeciesVM speciesVM) {
        Species species = speciesCreateVmMappers.toEntity(speciesVM);
        speciesService.save(species);
        return ResponseEntity.status(HttpStatus.CREATED).body("Species created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpeciesVM> getById(@PathVariable UUID id) {
        Species species = speciesService.findById(id);
        return ResponseEntity.ok(speciesCreateVmMappers.toVm(species));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateSpecies(@PathVariable UUID id, @RequestBody @Valid SpeciesVM speciesVM) {
        Species updatedSpecies = speciesCreateVmMappers.toEntity(speciesVM);
        speciesService.updateSpecies(id, updatedSpecies);
        return ResponseEntity.ok("Species updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSpecies(@PathVariable UUID id) {
        speciesService.delete(id);
        return ResponseEntity.ok("Species deleted successfully");
    }

    @GetMapping("/list")
    public ResponseEntity<List<SpeciesVM>> getAllSpecies(@RequestParam(required = false) SpeciesType type) {
        List<Species> speciesList = speciesService.findSpeciesByType(type);
        List<SpeciesVM> speciesVMList = speciesList.stream()
                .map(speciesCreateVmMappers::toVm)
                .collect(Collectors.toList());
        return ResponseEntity.ok(speciesVMList);
    }


}
