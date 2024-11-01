package com.java.hunters.service;

import com.java.hunters.domain.Species;
import com.java.hunters.repository.SpeciesRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SpeciesService {
    private final SpeciesRepo speciesRepo;

    public SpeciesService(SpeciesRepo speciesRepo){
        this.speciesRepo = speciesRepo;
    }

    public Species save(Species species){
        return speciesRepo.save(species);
    }

    public Optional<Species> findById(UUID id) {
        return speciesRepo.findById(id);
    }



}
