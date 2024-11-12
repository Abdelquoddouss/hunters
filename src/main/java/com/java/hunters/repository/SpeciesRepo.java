package com.java.hunters.repository;

import com.java.hunters.domain.Species;
import com.java.hunters.domain.enums.SpeciesType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SpeciesRepo extends JpaRepository<Species, UUID> {

    boolean existsByName(String name);

    List<Species> findByCategory(SpeciesType category);


}
