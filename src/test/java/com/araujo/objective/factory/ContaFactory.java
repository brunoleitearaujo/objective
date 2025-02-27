package com.araujo.objective.factory;

import com.araujo.objective.entity.Conta;

public class ContaFactory {

    public static Conta build() {
        var conta = new Conta();
        conta.setNumeroConta(1L);
        conta.setSaldo(100F);

        return conta;
    }
}
