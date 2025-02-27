package com.araujo.objective.factory;

import com.araujo.objective.controller.dto.ContaDTO;

public class ContaDTOFactory {

    public static ContaDTO build() {
        return new ContaDTO(1L, 100F);
    }
}
