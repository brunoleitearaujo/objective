package com.araujo.objective.mapper;

import com.araujo.objective.controller.dto.ContaDTO;
import com.araujo.objective.entity.Conta;

public class ContaMapper {

    public static Conta toConta(ContaDTO dto) {
        return new Conta(
                dto.numeroConta(),
                dto.saldo()
        );
    }

    public static ContaDTO toContaDTO(Conta c) {
        return new ContaDTO(
                c.getNumeroConta(),
                c.getSaldo()
        );
    }
}
