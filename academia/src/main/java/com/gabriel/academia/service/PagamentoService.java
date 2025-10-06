package com.gabriel.academia.service;

import com.gabriel.academia.DTO.PagamentoCreateDTO;
import com.gabriel.academia.DTO.PagamentoResponseDTO;
import com.gabriel.academia.entity.Aluno;
import com.gabriel.academia.entity.Pagamento;
import com.gabriel.academia.enums.StatusPagamento;
import com.gabriel.academia.repository.AlunoRepository;
import com.gabriel.academia.repository.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final AlunoRepository alunoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository, AlunoRepository alunoRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.alunoRepository = alunoRepository;
    }

    @Transactional
    public PagamentoResponseDTO registrarPagamento(PagamentoCreateDTO dto) {
        Aluno aluno = alunoRepository.findById(dto.alunoId())
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com o ID: " + dto.alunoId()));

        Pagamento pagamento = new Pagamento();
        pagamento.setAluno(aluno);
        pagamento.setValorPago(dto.valorPago());
        pagamento.setDataPagamento(LocalDate.now());

        pagamento.setStatus(StatusPagamento.PAGO);

        Pagamento salvo = pagamentoRepository.save(pagamento);
        return toResponseDTO(salvo);
    }

    @Transactional(readOnly = true)
    public List<PagamentoResponseDTO> listarPagamentosPorAluno(Long alunoId) {
        if (!alunoRepository.existsById(alunoId)) {
            throw new EntityNotFoundException("Aluno não encontrado com o ID: " + alunoId);
        }

        return alunoRepository.findById(alunoId).get().getPagamentos()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    private PagamentoResponseDTO toResponseDTO(Pagamento pagamento) {
        return new PagamentoResponseDTO(
                pagamento.getId(),
                pagamento.getValorPago(),
                pagamento.getDataPagamento(),
                pagamento.getAluno().getId(),
                pagamento.getStatus()
        );
    }
}