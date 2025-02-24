package com.araujo.objective.service;

import com.araujo.objective.Exception.ContaNaoEncontradaException;
import com.araujo.objective.Exception.NumeroDaContaJaExisteException;
import com.araujo.objective.controller.dto.ContaDTO;
import com.araujo.objective.entity.Conta;
import com.araujo.objective.mapper.ContaMapper;
import com.araujo.objective.repository.ContaRepository;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public ContaDTO createConta(ContaDTO contaDTO) {
        verificaSeContaJaExiste(contaDTO.numeroConta());

        var conta = contaRepository.save(ContaMapper.toConta(contaDTO));

        return ContaMapper.toContaDTO(conta);
    }

    private void verificaSeContaJaExiste(Long numeroConta) {
        var conta = contaRepository.findByNumeroConta(numeroConta);
        if (conta.isPresent()) {
            throw new NumeroDaContaJaExisteException();
        }
    }

    public ContaDTO getConta(Long numeroConta) {
        var conta = buscarConta(numeroConta);

        return ContaMapper.toContaDTO(conta);
    }

    public Conta buscarConta(Long numeroConta) {
        return contaRepository.findByNumeroConta(numeroConta)
                .orElseThrow(ContaNaoEncontradaException::new);
    }
}
