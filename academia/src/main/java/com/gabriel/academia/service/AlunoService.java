package com.gabriel.academia.service;

import com.gabriel.academia.DTO.AlunoCreateDTO;
import com.gabriel.academia.DTO.AlunoResponseDTO;
import com.gabriel.academia.DTO.AlunoUpdateDTO;
import com.gabriel.academia.entity.Aluno;
import com.gabriel.academia.entity.Plano;
import com.gabriel.academia.entity.Treino;
import com.gabriel.academia.repository.AlunoRepository;
import com.gabriel.academia.repository.PlanoRepository;
import com.gabriel.academia.repository.TreinoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final PlanoRepository planoRepository;
    private final TreinoRepository treinoRepository;

    public AlunoService(AlunoRepository alunoRepository, PlanoRepository planoRepository, TreinoRepository treinoRepository) {
        this.alunoRepository = alunoRepository;
        this.planoRepository = planoRepository;
        this.treinoRepository = treinoRepository;
    }

    @Transactional(readOnly = true)
    public List<AlunoResponseDTO> buscarTodos() {
        return alunoRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AlunoResponseDTO buscarPorId(Long id) {
        return alunoRepository.findById(id)
                .map(this::toResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com o ID: " + id));
    }

    @Transactional
    public AlunoResponseDTO criarAluno(AlunoCreateDTO dto) {
        alunoRepository.findByCpf(dto.cpf()).ifPresent(a -> {
            throw new IllegalArgumentException("Erro: CPF duplicado.");
        });

        Plano plano = planoRepository.findById(dto.planoId())
                .orElseThrow(() -> new EntityNotFoundException("Plano não encontrado com o ID: " + dto.planoId()));

        Aluno aluno = new Aluno();
        aluno.setNome(dto.nome());
        aluno.setCpf(dto.cpf());
        aluno.setPlano(plano);
        aluno.setAtivo(true);

        Aluno salvo = alunoRepository.save(aluno);
        return toResponseDTO(salvo);
    }

    @Transactional
    public AlunoResponseDTO atualizarAluno(Long id, AlunoUpdateDTO dto) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com o ID: " + id));

        Plano plano = planoRepository.findById(dto.planoId())
                .orElseThrow(() -> new EntityNotFoundException("Plano não encontrado com o ID: " + dto.planoId()));

        aluno.setNome(dto.nome());
        aluno.setPlano(plano);

        Aluno atualizado = alunoRepository.save(aluno);
        return toResponseDTO(atualizado);
    }

    @Transactional
    public void inativarAluno(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com o ID: " + id));
        aluno.setAtivo(false);
        alunoRepository.save(aluno);
    }

    @Transactional
    public AlunoResponseDTO associarTreino(Long alunoId, Long treinoId) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com o ID: " + alunoId));

        Treino treino = treinoRepository.findById(treinoId)
                .orElseThrow(() -> new EntityNotFoundException("Treino não encontrado com o ID: " + treinoId));

        aluno.getTreinos().add(treino);
        Aluno alunoAtualizado = alunoRepository.save(aluno);
        return toResponseDTO(alunoAtualizado);
    }

    private AlunoResponseDTO toResponseDTO(Aluno aluno) {
        return new AlunoResponseDTO(
                aluno.getId(),
                aluno.getNome(),
                aluno.getCpf(),
                aluno.isAtivo(),
                aluno.getPlano() != null ? aluno.getPlano().getId() : null,
                aluno.getTreinos().stream().map(Treino::getId).collect(Collectors.toSet())
        );
    }
}
