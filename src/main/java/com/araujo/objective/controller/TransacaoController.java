package com.araujo.objective.controller;

import com.araujo.objective.controller.dto.ContaDTO;
import com.araujo.objective.controller.dto.TransacaoDTO;
import com.araujo.objective.service.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<ContaDTO> createTransacao(@RequestBody @Valid TransacaoDTO transacaoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transacaoService.createTransacao(transacaoDTO));
    }
}
