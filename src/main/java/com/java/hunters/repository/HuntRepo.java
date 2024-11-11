package com.java.hunters.repository;

import com.java.hunters.domain.Hunt;
import com.java.hunters.domain.Species;
import com.java.hunters.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface HuntRepo extends JpaRepository<Hunt, UUID> {

    void deleteBySpecies(Species speciesToDelete);

    @Transactional
    @Modifying
    @Query("DELETE FROM Hunt h WHERE h.species.id = :speciesId")
    void deleteBySpeciesId(@Param("speciesId") UUID speciesId);
}
