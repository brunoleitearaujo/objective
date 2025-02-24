package com.araujo.objective.controller.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record ContaDTO(@NotNull Long numeroConta,
                       @DecimalMin("0.00") @NotNull Float saldo) {
}
