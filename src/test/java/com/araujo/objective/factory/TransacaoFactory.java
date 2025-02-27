package com.araujo.objective.factory;

import com.araujo.objective.entity.FormaPagamento;
import com.araujo.objective.entity.Transacao;

public class TransacaoFactory {

    public static Transacao buildTransacaoPix() {
        var formaPagamento = new FormaPagamento();
        formaPagamento.setId(1L);
        formaPagamento.setFormaPagamento("Pix");

        return new Transacao(ContaFactory.build(), formaPagamento, 10F);
    }

    public static Transacao buildTransacaoCredito() {
        var formaPagamento = new FormaPagamento();
        formaPagamento.setId(2L);
        formaPagamento.setFormaPagamento("Cartão de Crédito");

        return new Transacao(ContaFactory.build(), formaPagamento, 10F);
    }

    public static Transacao buildTransacaoDebito() {
        var formaPagamento = new FormaPagamento();
        formaPagamento.setId(3L);
        formaPagamento.setFormaPagamento("Cartão de Débito");

        return new Transacao(ContaFactory.build(), formaPagamento, 10F);
    }
}
