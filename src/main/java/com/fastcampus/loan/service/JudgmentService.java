package com.fastcampus.loan.service;

import com.fastcampus.loan.domain.Judgment;
import com.fastcampus.loan.dto.JudgmentDTO;

public interface JudgmentService {

    JudgmentDTO.Response create(JudgmentDTO.Request request);

    JudgmentDTO.Response get(Long judgmentId);

    JudgmentDTO.Response getJudgmentOfApplication(Long applicationId);

    JudgmentDTO.Response update(Long judgmentId, JudgmentDTO.Request request);

    void delete(Long judgmentId);
}
