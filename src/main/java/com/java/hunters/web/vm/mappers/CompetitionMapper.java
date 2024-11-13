package com.java.hunters.web.vm.mappers;

import com.java.hunters.domain.Competition;
import com.java.hunters.web.vm.CompetitionVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompetitionMapper {

    @Mapping(target = "id", ignore = true)  // Let the DB generate the ID
    Competition toEntity(CompetitionVM competitionVM);
}