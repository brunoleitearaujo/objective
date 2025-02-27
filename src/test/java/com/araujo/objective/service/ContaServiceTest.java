package com.araujo.objective.service;

import com.araujo.objective.Exception.ContaNaoEncontradaException;
import com.araujo.objective.Exception.NumeroDaContaJaExisteException;
import com.araujo.objective.entity.Conta;
import com.araujo.objective.factory.ContaDTOFactory;
import com.araujo.objective.factory.ContaFactory;
import com.araujo.objective.mapper.ContaMapper;
import com.araujo.objective.repository.ContaRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContaServiceTest {

    @Mock
    ContaRepository contaRepository;

    @InjectMocks
    ContaService contaService;

    @Captor
    ArgumentCaptor<Conta> contaArgumentCaptor;

    @Nested
    class CreateConta {

        @Test
        void deveChamarRepositorySaveComSucesso() {
            var conta = ContaFactory.build();

            contaRepository.save(conta);

            verify(contaRepository, times(1)).save(contaArgumentCaptor.capture());

            var contaCaptor = contaArgumentCaptor.getValue();

            assertEquals(conta.getNumeroConta(), contaCaptor.getNumeroConta());
            assertEquals(conta.getSaldo(), contaCaptor.getSaldo());
        }

        @Test
        void deveMapearDTOParaEntidadeComSucesso() {
            var contaDTO = ContaDTOFactory.build();

            var conta = ContaMapper.toConta(contaDTO);

            assertEquals(contaDTO.numeroConta(), conta.getNumeroConta());
            assertEquals(contaDTO.saldo(), conta.getSaldo());
        }

        @Test
        void deveMapearEntidadeParaDTOComSucesso() {
            var conta = ContaFactory.build();

            var contaDTO = ContaMapper.toContaDTO(conta);

            assertEquals(conta.getNumeroConta(), contaDTO.numeroConta());
            assertEquals(conta.getSaldo(), contaDTO.saldo());
        }

        @Test
        void deveAcionarExceptionSeContaJaExiste() {
            var contaDTO = ContaDTOFactory.build();
            var conta = ContaFactory.build();
            doReturn(Optional.of(conta)).when(contaRepository).findByNumeroConta(1L);

            NumeroDaContaJaExisteException e = assertThrows(NumeroDaContaJaExisteException.class, () -> {
                contaService.createConta(contaDTO);
            });

            assertEquals(e.toProblemDetail().getTitle(), "O numero da conta já existe");
        }
    }

    @Nested
    class GetConta {

        @Test
        void deveChamarRepositoryFindByNumeroContaComSucesso() {
            var conta = ContaFactory.build();

            doReturn(Optional.of(conta)).when(contaRepository).findByNumeroConta(1L);

            var response = contaService.getConta(1L);

            assertEquals(response.numeroConta(), conta.getNumeroConta());
            assertEquals(response.saldo(), conta.getSaldo());
        }

        @Test
        void deveAcionarExceptionSeContaNaoEncontrada() {
            ContaNaoEncontradaException e = assertThrows(ContaNaoEncontradaException.class, () -> {
                contaService.getConta(1L);
            });

            assertEquals(e.toProblemDetail().getTitle(), "Conta não encontrada");
        }
    }
}