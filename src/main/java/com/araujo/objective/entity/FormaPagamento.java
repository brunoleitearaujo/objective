package com.araujo.objective.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_forma_pagamento")
public class FormaPagamento {

    @Id
    private Long id;

    @Column(name = "forma_pagamento")
    private String formaPagamento;

    public FormaPagamento() {
    }

    public FormaPagamento(Long id, String formaPagamento) {
        this.id = id;
        this.formaPagamento = formaPagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public enum Enum {

        P(1L, "Pix"),
        C(2L, "Cartão de Crédito"),
        D(3L, "Cartão de Débito");

        Enum(Long id, String descricao) {
            this.id = id;
            this.descricao = descricao;
        }

        private Long id;
        private String descricao;

        public FormaPagamento getFormaPagamento() {
            return new FormaPagamento(id, descricao);
        }
    }
}
