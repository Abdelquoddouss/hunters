package com.java.hunters.web.vm;

import com.java.hunters.domain.enums.SpeciesType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CompetitionVM {

    @NotNull(message = "Code is required")
    private String code;

    @NotNull(message = "Location is required")
    private String location;

    @NotNull(message = "Date is required")
    private LocalDateTime date;

    @NotNull(message = "Species type is required")
    private SpeciesType speciesType;

    @NotNull(message = "Minimum participants are required")
    @Min(value = 1, message = "At least 1 participant is required")
    private Integer minParticipants;

    @NotNull(message = "Maximum participants are required")
    @Min(value = 1, message = "At least 1 participant is required")
    private Integer maxParticipants;

    @NotNull(message = "Open registration status is required")
    private Boolean openRegistration;
}