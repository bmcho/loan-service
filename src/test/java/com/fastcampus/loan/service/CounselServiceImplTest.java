package com.fastcampus.loan.service;

import com.fastcampus.loan.domain.Counsel;
import com.fastcampus.loan.dto.CounselDTO;
import com.fastcampus.loan.repository.CounselRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CounselServiceImplTest {

    @InjectMocks
    private CounselServiceImpl counselService;

    @Mock
    private CounselRepository counselRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void Should_ReturnResponseOfNewCounselEntity_When_RequestCounsel() {

        Counsel entity = Counsel.builder()
                .name("Test User")
                .cellPhone("010-1111-1111")
                .email("Test@gmail.com")
                .memo("테스트 입니다!")
                .zipCode("111111")
                .address("서울시 행복동 행복구")
                .addressDetail("50번길")
                .build();

        CounselDTO.Request request = CounselDTO.Request.builder()
                .name("Test User")
                .cellPhone("010-1111-1111")
                .email("Test@gmail.com")
                .memo("테스트 입니다!")
                .zipCode("111111")
                .address("서울시 행복동 행복구")
                .addressDetail("50번길")
                .build();

        when(counselRepository.save(any(Counsel.class))).thenReturn(entity);

        CounselDTO.Response actual = counselService.create(request);

        assertThat(actual.getName()).isSameAs(entity.getName());

    }

}