package com.gabriel.academia.DTO;

import com.gabriel.academia.enums.StatusPagamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PagamentoResponseDTO(
        Long id,
        BigDecimal valorPago,
        LocalDate dataPagamento,
        Long alunoId,
        StatusPagamento status
) {
}
