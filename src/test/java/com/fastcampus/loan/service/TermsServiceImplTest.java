package com.fastcampus.loan.service;

import com.fastcampus.loan.domain.Terms;
import com.fastcampus.loan.dto.TermsDTO;
import com.fastcampus.loan.repository.TermsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TermsServiceImplTest {

    @InjectMocks
    private TermsServiceImpl termsService;

    @Mock
    private TermsRepository termsRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void should_ReturnResponseEntity_When_RequestCreatedTerms() {

        Terms entity = Terms.builder()
                .name("새로운 약관")
                .termsDetailUrl("https://www.naver.com")
                .build();

        TermsDTO.Request request = TermsDTO.Request.builder()
                .name("새로운 약관")
                .termsDetailUrl("https://www.naver.com")
                .build();

        when(termsRepository.save(any(Terms.class))).thenReturn(entity);

        TermsDTO.Response response = termsService.create(request);

        assertThat(response.getName()).isEqualTo(request.getName());

    }

}