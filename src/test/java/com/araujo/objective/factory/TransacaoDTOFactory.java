package com.araujo.objective.factory;

import com.araujo.objective.controller.dto.TransacaoDTO;
import com.araujo.objective.entity.FormaPagamento;

public class TransacaoDTOFactory {

    public static TransacaoDTO build(FormaPagamento.Enum formaPagamento, Float valor) {
        return new TransacaoDTO(1L, formaPagamento, valor);
    }
}
