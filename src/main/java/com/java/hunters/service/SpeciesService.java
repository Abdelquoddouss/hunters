package com.java.hunters.service;

import com.java.hunters.domain.Species;
import com.java.hunters.domain.enums.SpeciesType;
import com.java.hunters.exception.DuplicateResourceException;
import com.java.hunters.exception.ResourceNotFoundException;
import com.java.hunters.repository.HuntRepo;
import com.java.hunters.repository.SpeciesRepo;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SpeciesService {

    private final SpeciesRepo speciesRepo;

    private final HuntRepo huntRepo;

    @Transactional
    public Species save(Species species) {
        validateSpecies(species);

        if (speciesRepo.existsByName(species.getName())) {
            throw new DuplicateResourceException("Species with name '" + species.getName() + "' already exists.");
        }

        return speciesRepo.save(species);
    }

    @Transactional(readOnly = true)
    public Species findById(UUID id) {
        return speciesRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Species with id '" + id + "' does not exist."));
    }

    @Transactional
    public Species updateSpecies(UUID id, Species updatedSpecies) {
        Species existingSpecies = findById(id); // use findById to handle exception
        validateSpecies(updatedSpecies);

        existingSpecies.setName(updatedSpecies.getName());
        existingSpecies.setCategory(updatedSpecies.getCategory());
        existingSpecies.setMinimumWeight(updatedSpecies.getMinimumWeight());
        existingSpecies.setDifficulty(updatedSpecies.getDifficulty());
        existingSpecies.setPoints(updatedSpecies.getPoints());

        return speciesRepo.save(existingSpecies);
    }

    @Transactional
    public void delete(UUID id) {
        Species speciesToDelete = findById(id); // use findById to handle exception

        try {
            huntRepo.deleteBySpeciesId(speciesToDelete.getId());
            speciesRepo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("Cannot delete species as it is referenced in other records.");
        }
    }

    private void validateSpecies(Species species) {
        if (!StringUtils.hasText(species.getName())) {
            throw new IllegalArgumentException("Name is required.");
        }
        if (species.getCategory() == null) {
            throw new IllegalArgumentException("Category is required.");
        }
        if (species.getMinimumWeight() == null || species.getMinimumWeight() <= 0) {
            throw new IllegalArgumentException("Minimum weight must be a positive value.");
        }
        if (species.getDifficulty() == null) {
            throw new IllegalArgumentException("Difficulty is required.");
        }
        if (species.getPoints() == null || species.getPoints() < 0) {
            throw new IllegalArgumentException("Points must be a non-negative value.");
        }
    }

    @Transactional(readOnly = true)
    public List<Species> findSpeciesByType(SpeciesType type) {
        if (type != null) {
            return speciesRepo.findByCategory(type);
        } else {
            return speciesRepo.findAll();
        }
    }

}
