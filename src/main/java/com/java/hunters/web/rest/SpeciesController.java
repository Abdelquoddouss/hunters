package com.java.hunters.web.rest;

import com.java.hunters.domain.Species;
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
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/species")
@AllArgsConstructor
public class SpeciesController {

  private final SpeciesService speciesService;
  private final SpeciesCreateVmMappers speciesCreateVmMappers;


    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody @Valid SpeciesVM speciesVM){
        Species species = speciesCreateVmMappers.toEntity(speciesVM);
        speciesService.save(species);
        return ResponseEntity.ok("Species created successfully");
    }


    @GetMapping("/{id}")
    public ResponseEntity<SpeciesVM> getById(@PathVariable @Valid UUID id) {
        Optional<Species> species = speciesService.findById(id);
        return species.map(value -> ResponseEntity.ok(speciesCreateVmMappers.toVm(value)))
                .orElse(ResponseEntity.notFound().build());
    }


    


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
