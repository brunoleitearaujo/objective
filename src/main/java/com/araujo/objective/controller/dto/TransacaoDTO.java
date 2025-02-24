package com.araujo.objective.controller.dto;

import com.araujo.objective.entity.FormaPagamento;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record TransacaoDTO(@NotNull Long numeroConta,
                           @NotNull FormaPagamento.Enum formaPagamento,
                           @DecimalMin("0.01") @NotNull Float valor) {
}
