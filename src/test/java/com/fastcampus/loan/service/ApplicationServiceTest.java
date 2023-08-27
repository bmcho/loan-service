package com.fastcampus.loan.service;

import com.fastcampus.loan.domain.Application;
import com.fastcampus.loan.domain.Terms;
import com.fastcampus.loan.dto.ApplicationDTO;
import com.fastcampus.loan.repository.AcceptTermsRepository;
import com.fastcampus.loan.repository.ApplicationRepository;
import com.fastcampus.loan.repository.TermsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceTest {

    @InjectMocks
    private ApplicationServiceImpl applicationService;

    @Mock
    private ApplicationRepository applicationRepository;
    @Mock
    private TermsRepository termsRepository;
    @Mock
    private AcceptTermsRepository acceptTermsRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void should_ReturnNewApplicationEntity_When_NewApplicationCreated() {
        Application entity = Application.builder()
                .name("Test Name")
                .cellPhone("0000-1111-2222")
                .email("test@gail.com")
                .hopeAmount(BigDecimal.valueOf(5000000))
                .build();


        ApplicationDTO.Request request = ApplicationDTO.Request.builder()
                .name("Test Name")
                .cellPhone("0000-1111-2222")
                .email("test@gail.com")
                .hopeAmount(BigDecimal.valueOf(5000000))
                .build();

        when(applicationRepository.save(any(Application.class))).thenReturn(entity);
        ApplicationDTO.Response actual = applicationService.create(request);
        assertThat(actual.getHopeAmount()).isEqualTo(entity.getHopeAmount());
    }

    @Test
    void should_ReturnResponseEntity_When_RequestApplicationId() {
        Long applicationId = 1L;

        Application entity = Application.builder()
                .applicationId(applicationId)
                .name("Test Name")
                .cellPhone("0000-1111-2222")
                .email("test@gail.com")
                .hopeAmount(BigDecimal.valueOf(5000000))
                .build();

        when(applicationRepository.findById(applicationId)).thenReturn(Optional.ofNullable(entity));

        ApplicationDTO.Response actual = applicationService.get(applicationId);

        assertThat(actual.getApplicationId()).isEqualTo(applicationId);
    }

    @Test
    void should_ReturnResponseEntity_When_RequestAndUpdateEntity() {
        Long applicationId = 1L;

        Application entity = Application.builder()
                .applicationId(applicationId)
                .name("Test Name")
                .cellPhone("0000-1111-2222")
                .email("test@gail.com")
                .hopeAmount(BigDecimal.valueOf(5000000))
                .build();

        ApplicationDTO.Request request = ApplicationDTO.Request.builder()
                .name("Test Name")
                .cellPhone("0000-1111-2222")
                .email("test@gail.com")
                .hopeAmount(BigDecimal.valueOf(1000000))
                .build();

        when(applicationRepository.findById(applicationId)).thenReturn(Optional.ofNullable(entity));
        ApplicationDTO.Response actual = applicationService.update(applicationId, request);

        assertThat(actual.getHopeAmount()).isEqualTo(BigDecimal.valueOf(1000000));
    }

    @Test
    void should_DeleteApplicationEntity_When_RequestApplicationId() {
        Long applicationId = 1L;

        Application entity = Application.builder()
                .applicationId(applicationId)
                .name("Test Name")
                .cellPhone("0000-1111-2222")
                .email("test@gail.com")
                .hopeAmount(BigDecimal.valueOf(5000000))
                .build();

        when(applicationRepository.findById(applicationId)).thenReturn(Optional.ofNullable(entity));
        applicationService.delete(applicationId);

        assertThat(entity.getIsDeleted()).isEqualTo(true);
    }

    @Test
    void Should_AddAcceptTerms_When_RequestAcceptTermsOfApplication() {
        Terms entity1 = Terms.builder()
                .termsId(1L)
                .name("약관1")
                .termsDetailUrl("https://naver.com")
                .build();

        Terms entity2 = Terms.builder()
                .termsId(2L)
                .name("약관1")
                .termsDetailUrl("https://naver.com")
                .build();

        List<Long> acceptTerms = Arrays.asList(1L, 2L);

        ApplicationDTO.AcceptTerms request = ApplicationDTO.AcceptTerms.builder()
                .acceptTermsIds(acceptTerms)
                .build();

        Long findId = 1L;

        when(applicationRepository.findById(findId)).thenReturn(Optional.ofNullable(Application.builder().applicationId(findId).build()));
        when(termsRepository.findAll(Sort.by(Sort.Direction.ASC, "termsId"))).thenReturn(List.of(entity1, entity2));

        Boolean actual = applicationService.acceptTerms(findId, request);

        assertThat(actual).isTrue();

    }

}
