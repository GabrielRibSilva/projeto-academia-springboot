package com.gabriel.academia.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AlunoCreateDTO(
        @NotBlank(message = "O nome não pode estar em branco")
        String nome,

        @NotBlank(message = "O CPF não pode estar em branco")
        @Size(min = 11, max = 11, message = "O CPF deve ter 11 dígitos")
        String cpf,

        @NotNull(message = "O ID do plano não pode ser nulo")
        Long planoId
) {

}
