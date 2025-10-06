package com.gabriel.academia.DTO;

import com.gabriel.academia.enums.NivelTreino;

public record TreinoResponseDTO(Long id, String nome, NivelTreino nivel) {

}
