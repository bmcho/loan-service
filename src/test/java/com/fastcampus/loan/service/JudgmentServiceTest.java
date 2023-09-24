package com.fastcampus.loan.service;

import com.fastcampus.loan.domain.Application;
import com.fastcampus.loan.domain.Judgment;
import com.fastcampus.loan.dto.JudgmentDTO;
import com.fastcampus.loan.repository.ApplicationRepository;
import com.fastcampus.loan.repository.JudgmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JudgmentServiceTest {

    @InjectMocks
    private JudgmentServiceImpl judgmentService;

    @Mock
    private JudgmentRepository judgmentRepository;

    @Mock
    private ApplicationRepository applicationRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void Should_ReturnResponseNewJudgmentEntity_When_RequestNewJudgment() {

        Judgment judgment = Judgment.builder()
                .applicationId(1L)
                .name("Test Mem")
                .approvalAmount(new BigDecimal("5000000"))
                .build();

        JudgmentDTO.Request request = JudgmentDTO.Request.builder()
                .applicationId(1L)
                .name("Test Mem")
                .approvalAmount(new BigDecimal("5000000"))
                .build();

        when(applicationRepository.findById(1L)).thenReturn(Optional.ofNullable(Application.builder().build()));
        when(judgmentRepository.save(any(Judgment.class))).thenReturn(judgment);

        JudgmentDTO.Response actual = judgmentService.create(request);

        assertThat(actual.getApplicationId()).isEqualTo(judgment.getApplicationId());
    }

    @Test
    void Should_ReturnResponseJudgmentEntity_When_RequestExistJudgment() {
        Judgment judgment = Judgment.builder()
                .judgmentId(1L)
                .build();

        when(judgmentRepository.findById(1L)).thenReturn(Optional.ofNullable(judgment));

        JudgmentDTO.Response actual = judgmentService.get(1L);

        assertThat(actual.getJudgmentId()).isEqualTo(1L);
    }

    @Test
    void Should_ReturnResponseJudgmentEntity_When_RequestExistApplication() {
        Judgment judgment = Judgment.builder()
                .judgmentId(2L)
                .applicationId(1L)
                .build();

        when(applicationRepository.findById(1L)).thenReturn(Optional.ofNullable(Application.builder().build()));
        when(judgmentRepository.findByApplicationId(1L)).thenReturn(Optional.ofNullable(judgment));

        JudgmentDTO.Response actual = judgmentService.getJudgmentOfApplication(1L);
        assertThat(actual.getJudgmentId()).isEqualTo(2L);
        assertThat(actual.getApplicationId()).isEqualTo(1L);
    }

    @Test
    void Should_ReturnResponseJudgmentEntity_When_RequestUpdateExistJudgmentInfo() {

        Judgment judgment = Judgment.builder()
                .judgmentId(1L)
                .name("Before Test Mem")
                .approvalAmount(new BigDecimal("1000000"))
                .build();


        JudgmentDTO.Request request = JudgmentDTO.Request.builder()
                .name("Test Mem")
                .approvalAmount(new BigDecimal("5000000"))
                .build();


        when(judgmentRepository.findById(1L)).thenReturn(Optional.ofNullable(judgment));
        when(judgmentRepository.save(any(Judgment.class))).thenReturn(judgment);

        JudgmentDTO.Response actual = judgmentService.update(1L, request);

        assertThat(actual.getJudgmentId()).isEqualTo(1L);
        assertThat(actual.getName()).isEqualTo(request.getName());
        assertThat(actual.getApprovalAmount()).isEqualTo(request.getApprovalAmount());
    }

}
