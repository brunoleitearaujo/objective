package com.araujo.objective.controller;

import com.araujo.objective.controller.dto.ContaDTO;
import com.araujo.objective.service.ContaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ContaDTO> createConta(@RequestBody @Valid ContaDTO contaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contaService.createConta(contaDTO));
    }
}
