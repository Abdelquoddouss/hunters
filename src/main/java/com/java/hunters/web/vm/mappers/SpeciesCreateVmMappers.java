package com.java.hunters.web.vm.mappers;

import com.java.hunters.domain.Species;
import com.java.hunters.web.vm.SpeciesVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpeciesCreateVmMappers {

    Species toEntity(SpeciesVM speciesVM);

    SpeciesVM toVm(Species species);

}
