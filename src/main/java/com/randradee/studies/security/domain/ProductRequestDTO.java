package com.randradee.studies.security.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequestDTO(
        @NotBlank
        String name,
        @NotNull
        Double price
) {
}
