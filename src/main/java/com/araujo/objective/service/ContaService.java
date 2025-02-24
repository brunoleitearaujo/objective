package com.araujo.objective.service;

import com.araujo.objective.Exception.NumeroDaContaJaExisteException;
import com.araujo.objective.controller.dto.ContaDTO;
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
        validaConta(contaDTO.numeroConta());

        var conta = contaRepository.save(ContaMapper.toConta(contaDTO));

        return ContaMapper.toContaDTO(conta);
    }

    private void validaConta(Long numeroConta) {
        var conta = contaRepository.findByNumeroConta(numeroConta);
        if (conta.isPresent()) {
            throw new NumeroDaContaJaExisteException();
        }
    }
}
