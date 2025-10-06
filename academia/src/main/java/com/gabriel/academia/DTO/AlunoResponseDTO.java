package com.gabriel.academia.DTO;

import java.util.Set;

public record AlunoResponseDTO(
        Long id,
        String nome,
        String cpf,
        boolean ativo,
        Long planoId,
        Set<Long> treinoIds
) {

}
