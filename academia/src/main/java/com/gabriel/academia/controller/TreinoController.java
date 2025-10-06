package com.gabriel.academia.controller;

import com.gabriel.academia.DTO.TreinoCreateDTO;
import com.gabriel.academia.DTO.TreinoResponseDTO;
import com.gabriel.academia.service.TreinoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/treinos")
public class TreinoController {

    private final TreinoService treinoService;

    public TreinoController(TreinoService treinoService) {
        this.treinoService = treinoService;
    }

    @PostMapping
    public ResponseEntity<TreinoResponseDTO> criarTreino(@Valid @RequestBody TreinoCreateDTO dto) {
        return new ResponseEntity<>(treinoService.salvar(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TreinoResponseDTO>> listarTreinos() {
        return ResponseEntity.ok(treinoService.listarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTreino(@PathVariable Long id) {
        treinoService.deletarTreino(id);
        return ResponseEntity.noContent().build();
    }
}