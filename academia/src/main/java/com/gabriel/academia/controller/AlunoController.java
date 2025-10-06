package com.gabriel.academia.controller;

import com.gabriel.academia.DTO.AlunoCreateDTO;
import com.gabriel.academia.DTO.AlunoResponseDTO;
import com.gabriel.academia.DTO.AlunoUpdateDTO;
import com.gabriel.academia.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public ResponseEntity<AlunoResponseDTO> criarAluno(@Valid @RequestBody AlunoCreateDTO alunoDTO) {
        AlunoResponseDTO novoAluno = alunoService.criarAluno(alunoDTO);
        return new ResponseEntity<>(novoAluno, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponseDTO>> buscarTodosAlunos() {
        List<AlunoResponseDTO> alunos = alunoService.buscarTodos();
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> buscarAlunoPorId(@PathVariable Long id) {
        AlunoResponseDTO aluno = alunoService.buscarPorId(id);
        return ResponseEntity.ok(aluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> atualizarAluno(@PathVariable Long id, @Valid @RequestBody AlunoUpdateDTO alunoDTO) {
        AlunoResponseDTO alunoAtualizado = alunoService.atualizarAluno(id, alunoDTO);
        return ResponseEntity.ok(alunoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativarAluno(@PathVariable Long id) {
        alunoService.inativarAluno(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{alunoId}/treinos")
    public ResponseEntity<AlunoResponseDTO> associarTreino(@PathVariable Long alunoId, @RequestBody Map<String, Long> payload) {
        Long treinoId = payload.get("treinoId");
        if (treinoId == null) {
            return ResponseEntity.badRequest().build();
        }
        AlunoResponseDTO alunoAtualizado = alunoService.associarTreino(alunoId, treinoId);
        return ResponseEntity.ok(alunoAtualizado);
    }
}
