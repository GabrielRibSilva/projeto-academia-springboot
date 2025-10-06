package com.gabriel.academia.DTO;

import java.math.BigDecimal;

public record PagamentoCreateDTO(BigDecimal valorPago, Long alunoId) {
}