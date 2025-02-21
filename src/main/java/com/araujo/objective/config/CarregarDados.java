package com.araujo.objective.config;

import com.araujo.objective.entity.FormaPagamento;
import com.araujo.objective.repository.FormaPagamentoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class CarregarDados implements CommandLineRunner {

    private final FormaPagamentoRepository formaPagamentoRepository;

    public CarregarDados(FormaPagamentoRepository formaPagamentoRepository) {
        this.formaPagamentoRepository = formaPagamentoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(FormaPagamento.Enum.values())
                .forEach(formaPagamento -> formaPagamentoRepository.save(formaPagamento.getFormaPagamento()));
    }
}
