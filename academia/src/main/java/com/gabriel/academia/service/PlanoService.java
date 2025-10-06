package com.gabriel.academia.service;

import com.gabriel.academia.DTO.PlanoDTO;
import com.gabriel.academia.entity.Plano;
import com.gabriel.academia.repository.PlanoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanoService {

    private final PlanoRepository planoRepository;

    public PlanoService(PlanoRepository planoRepository) {
        this.planoRepository = planoRepository;
    }

    @Transactional(readOnly = true)
    public List<PlanoDTO> listarTodos() {
        return planoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PlanoDTO buscarPorId(Long id) {
        return planoRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Plano não encontrado com o ID: " + id));
    }

    @Transactional
    public PlanoDTO salvar(PlanoDTO dto) {
        Plano plano = new Plano();
        plano.setNome(dto.nome());
        plano.setValor(dto.valor());

        Plano salvo = planoRepository.save(plano);
        return toDTO(salvo);
    }

    @Transactional
    public void deletar(Long id) {
        if (!planoRepository.existsById(id)) {
            throw new EntityNotFoundException("Plano não encontrado com o ID: " + id);
        }
        planoRepository.deleteById(id);
    }

    private PlanoDTO toDTO(Plano plano) {
        return new PlanoDTO(plano.getId(), plano.getNome(), plano.getValor());
    }
}
