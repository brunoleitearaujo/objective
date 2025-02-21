package com.araujo.objective.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_conta")
public class Conta {

    @Id
    @Column(name = "numero_conta_id")
    private Long numeroConta;

    @Column(name = "saldo")
    private Float saldo;

    public Conta() {
    }

    public Conta(Long numeroConta, Float saldo) {
        this.numeroConta = numeroConta;
        this.saldo = saldo;
    }

    public Long getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Long numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }
}
