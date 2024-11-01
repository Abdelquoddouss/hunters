package com.java.hunters.web.vm;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpeciesVM {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Category is required")
    private String category;

    @NotNull(message = "Minimum weight is required")
    private Double minimumWeight;

    @NotBlank(message = "Difficulty is required")
    private String difficulty;

    @NotNull(message = "Points are required")
    private Integer points;
}
