package com.araujo.objective.service;

import com.araujo.objective.Exception.SaldoInsuficienteException;
import com.araujo.objective.entity.Conta;
import com.araujo.objective.entity.FormaPagamento;
import com.araujo.objective.entity.Transacao;
import com.araujo.objective.factory.ContaFactory;
import com.araujo.objective.factory.TransacaoDTOFactory;
import com.araujo.objective.factory.TransacaoFactory;
import com.araujo.objective.repository.ContaRepository;
import com.araujo.objective.repository.TransacaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransacaoServiceTest {

    @Mock
    TransacaoRepository transacaoRepository;

    @Mock
    ContaRepository contaRepository;

    @Mock
    ContaService contaService;

    @InjectMocks
    TransacaoService transacaoService;

    @Captor
    ArgumentCaptor<Transacao> transacaoArgumentCaptor;

    @Test
    void deveChamarRepositorySaveComSucesso() {
        var transacao = TransacaoFactory.buildTransacaoPix();

        transacaoRepository.save(transacao);

        verify(transacaoRepository, times(1)).save(transacaoArgumentCaptor.capture());

        var transacaoCaptor = transacaoArgumentCaptor.getValue();

        assertEquals(transacao.getId(), transacaoCaptor.getId());
        assertEquals(transacao.getConta(), transacaoCaptor.getConta());
        assertEquals(transacao.getFormaPagamento(), transacaoCaptor.getFormaPagamento());
        assertEquals(transacao.getValor(), transacaoCaptor.getValor());
    }

    @Test
    void deveValidarTransacaoPixComSucesso() {
        var transacaoDTO = TransacaoDTOFactory.build(FormaPagamento.Enum.P, 10F);
        var conta = ContaFactory.build();
        var contaNovoSaldo = new Conta(1L, 90F);
        var transacao = TransacaoFactory.buildTransacaoPix();

        doReturn(conta).when(contaService).buscarConta(anyLong());
        doReturn(contaNovoSaldo).when(contaRepository).save(any());
        doReturn(transacao).when(transacaoRepository).save(any());

        var c = transacaoService.createTransacao(transacaoDTO);

        assertEquals(90F, c.saldo());
    }

    @Test
    void deveValidarTransacaoCreditoComSucesso() {
        var transacaoDTO = TransacaoDTOFactory.build(FormaPagamento.Enum.C, 10F);
        var conta = ContaFactory.build();

        doReturn(conta).when(contaService).buscarConta(anyLong());

        var response = transacaoService.createTransacao(transacaoDTO);

        assertEquals(89.5F, response.saldo());
    }

    @Test
    void deveValidarTransacaoDebitoComSucesso() {
        var transacaoDTO = TransacaoDTOFactory.build(FormaPagamento.Enum.D, 10F);
        var conta = ContaFactory.build();

        doReturn(conta).when(contaService).buscarConta(anyLong());

        var response = transacaoService.createTransacao(transacaoDTO);

        assertEquals(89.7F, response.saldo());
    }

    @Test
    void deveAcionarExceptionSaldoInsuficiente() {
        var transacaoDTO = TransacaoDTOFactory.build(FormaPagamento.Enum.C, 100F);
        var conta = ContaFactory.build();

        doReturn(conta).when(contaService).buscarConta(anyLong());

        SaldoInsuficienteException e = assertThrows(SaldoInsuficienteException.class, () -> {
            transacaoService.createTransacao(transacaoDTO);
        });

        assertEquals(e.toProblemDetail().getTitle(), "Saldo insufuciente");
    }
}