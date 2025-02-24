package com.araujo.objective.controller;

import com.araujo.objective.controller.dto.ContaDTO;
import com.araujo.objective.controller.dto.TransacaoDTO;
import com.araujo.objective.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(description = "Endpoint responsável por criar transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transação criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "404", description = "Campos não atendem os requisitos da transação"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<ContaDTO> createTransacao(@RequestBody @Valid TransacaoDTO transacaoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transacaoService.createTransacao(transacaoDTO));
    }
}
