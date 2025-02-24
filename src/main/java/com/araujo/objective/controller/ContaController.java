package com.araujo.objective.controller;

import com.araujo.objective.controller.dto.ContaDTO;
import com.araujo.objective.service.ContaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conta")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping
    @Operation(description = "Endpoint responsável por criar conta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Conta criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "422", description = "Campos não atendem os requisitos da conta"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<ContaDTO> createConta(@RequestBody @Valid ContaDTO contaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contaService.createConta(contaDTO));
    }

    @GetMapping("/{numeroConta}")
    @Operation(description = "Endpoint responsável por buscar conta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca da conta efetuada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "404", description = "Campos não atendem os requisitos da busca da conta"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<ContaDTO> getConta(@PathVariable Long numeroConta) {
        return ResponseEntity.ok().body(contaService.getConta(numeroConta));
    }
}
