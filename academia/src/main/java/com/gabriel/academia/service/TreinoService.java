package com.gabriel.academia.service;

import com.gabriel.academia.DTO.TreinoCreateDTO;
import com.gabriel.academia.DTO.TreinoResponseDTO;
import com.gabriel.academia.entity.Treino;
import com.gabriel.academia.repository.TreinoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreinoService {

    private final TreinoRepository treinoRepository;

    public TreinoService(TreinoRepository treinoRepository) {
        this.treinoRepository = treinoRepository;
    }

    @Transactional(readOnly = true)
    public List<TreinoResponseDTO> listarTodos() {
        return treinoRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public TreinoResponseDTO salvar(TreinoCreateDTO dto) {
        Treino treino = new Treino();
        treino.setNome(dto.nome());
        treino.setNivel(dto.nivel());

        Treino salvo = treinoRepository.save(treino);
        return toResponseDTO(salvo);
    }

    @Transactional
    public void deletarTreino(Long id) {
        Treino treino = treinoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Treino não encontrado com o ID: " + id));

        if (!treino.getAlunos().isEmpty()) {
            throw new IllegalStateException("Não é possível remover treinos que ainda estejam associados a alunos.");
        }

        treinoRepository.delete(treino);
    }

    private TreinoResponseDTO toResponseDTO(Treino treino) {
        return new TreinoResponseDTO(treino.getId(), treino.getNome(), treino.getNivel());
    }
}