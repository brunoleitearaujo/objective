package com.araujo.objective.service;

import com.araujo.objective.Exception.SaldoInsuficienteException;
import com.araujo.objective.controller.dto.ContaDTO;
import com.araujo.objective.controller.dto.TransacaoDTO;
import com.araujo.objective.entity.Transacao;
import com.araujo.objective.mapper.ContaMapper;
import com.araujo.objective.repository.ContaRepository;
import com.araujo.objective.repository.TransacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final ContaRepository contaRepository;
    private final ContaService contaService;

    public TransacaoService(TransacaoRepository transacaoRepository, ContaRepository contaRepository, ContaService contaService) {
        this.transacaoRepository = transacaoRepository;
        this.contaRepository = contaRepository;
        this.contaService = contaService;
    }

    @Transactional
    public ContaDTO createTransacao(TransacaoDTO transacaoDTO) {
        var conta = contaService.buscarConta(transacaoDTO.numeroConta());

        var novoSaldo = validaTransacao(transacaoDTO, conta.getSaldo());
        conta.setSaldo(novoSaldo);
        contaRepository.save(conta);

        var transacao = new Transacao(conta, transacaoDTO.formaPagamento().getFormaPagamento(), transacaoDTO.valor());
        transacaoRepository.save(transacao);

        return ContaMapper.toContaDTO(conta);
    }

    private float validaTransacao(TransacaoDTO transacaoDTO, float saldo) {
        var valor = transacaoDTO.valor();

        var novoSaldo = switch (transacaoDTO.formaPagamento()) {
            case P -> saldo - valor;
            case C -> saldo - (valor + calcularAcrescimo(valor, 5));
            case D -> saldo - (valor + calcularAcrescimo(valor, 3));
        };

        if (novoSaldo < 0) {
            throw new SaldoInsuficienteException();
        }

        return novoSaldo;
    }

    private float calcularAcrescimo(float valor, float taxa) {
        return (valor * taxa) / 100;
    }
}
