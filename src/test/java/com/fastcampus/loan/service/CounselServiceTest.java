package com.fastcampus.loan.service;

import com.fastcampus.loan.domain.Counsel;
import com.fastcampus.loan.dto.CounselDTO;
import com.fastcampus.loan.exception.BaseException;
import com.fastcampus.loan.exception.ResultType;
import com.fastcampus.loan.repository.CounselRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CounselServiceTest {

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

    @Test
    void should_ReturnResponseOfExistCounselEntity_When_RequestExistCounselId() {
        Long findId = 1L;
        Counsel entity = Counsel.builder()
                .counselId(1L)
                .build();

        when(counselRepository.findById(findId)).thenReturn(Optional.ofNullable(entity));

        CounselDTO.Response actual = counselService.get(findId);
        assertThat(actual.getCounselId()).isSameAs(findId);
    }

    @Test
    void should_ThrowException_When_RequestNotExistCounselId() {
        Long findId = 2L;
        Counsel entity = Counsel.builder()
                .counselId(1L)
                .build();

        when(counselRepository.findById(findId)).thenThrow(new BaseException(ResultType.SYSTEM_ERROR));

        Assertions.assertThrows(BaseException.class, () -> counselService.get(findId));
    }

    @Test
    void should_ReturnUpdateResponseCounselEntity_When_RequestUpdateCounselInfo() {
        Long findId = 1L;
        Counsel entity = Counsel.builder()
                .counselId(1L)
                .name("Test User")
                .cellPhone("010-1111-1111")
                .email("Test@gmail.com")
                .memo("테스트 입니다!")
                .zipCode("111111")
                .address("서울시 행복동 행복구")
                .addressDetail("50번길")
                .build();

        CounselDTO.Request request = CounselDTO.Request.builder()
                .name("Test User Modified")
                .cellPhone("010-1111-1111")
                .email("Test@gmail.com")
                .memo("테스트 입니다!")
                .zipCode("111111")
                .address("서울시 행복동 행복구")
                .addressDetail("50번길")
                .build();

        when(counselRepository.findById(findId)).thenReturn(Optional.ofNullable(entity));

        CounselDTO.Response actual = counselService.update(findId, request);

        assertThat(actual.getCounselId()).isEqualTo(findId);
        assertThat(actual.getName()).isEqualTo(request.getName());
    }

    @Test
    void should_DeleteCounselEntity_When_RequestDeleteCounsel() {
        Long findId = 1L;
        Counsel entity = Counsel.builder()
                .counselId(1L)
                .name("Test User")
                .cellPhone("010-1111-1111")
                .email("Test@gmail.com")
                .memo("테스트 입니다!")
                .zipCode("111111")
                .address("서울시 행복동 행복구")
                .addressDetail("50번길")
                .build();

        when(counselRepository.findById(findId)).thenReturn(Optional.ofNullable(entity));
        counselService.delete(findId);

        assertThat(entity.getIsDeleted()).isEqualTo(true);

    }

}