package com.araujo.objective.service;

import com.araujo.objective.Exception.ContaNaoEncontradaException;
import com.araujo.objective.Exception.NumeroDaContaJaExisteException;
import com.araujo.objective.controller.dto.ContaDTO;
import com.araujo.objective.entity.Conta;
import com.araujo.objective.mapper.ContaMapper;
import com.araujo.objective.repository.ContaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

    private static final Logger logger = LoggerFactory.getLogger(ContaService.class);

    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public ContaDTO createConta(ContaDTO contaDTO) {
        logger.info("Iniciando o processo de criar conta");

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
        logger.info("Iniciando o processo de buscar conta");

        var conta = buscarConta(numeroConta);

        return ContaMapper.toContaDTO(conta);
    }

    public Conta buscarConta(Long numeroConta) {
        return contaRepository.findByNumeroConta(numeroConta)
                .orElseThrow(ContaNaoEncontradaException::new);
    }
}
