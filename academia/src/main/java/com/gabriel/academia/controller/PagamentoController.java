package com.gabriel.academia.controller;

import com.gabriel.academia.DTO.PagamentoCreateDTO;
import com.gabriel.academia.DTO.PagamentoResponseDTO;
import com.gabriel.academia.service.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping
    public ResponseEntity<PagamentoResponseDTO> registrarPagamento(@Valid @RequestBody PagamentoCreateDTO dto) {
        PagamentoResponseDTO response = pagamentoService.registrarPagamento(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<PagamentoResponseDTO>> listarPagamentosPorAluno(@PathVariable Long alunoId) {
        List<PagamentoResponseDTO> pagamentos = pagamentoService.listarPagamentosPorAluno(alunoId);
        return ResponseEntity.ok(pagamentos);
    }
}
