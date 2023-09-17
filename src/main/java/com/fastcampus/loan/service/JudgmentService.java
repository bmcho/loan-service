package com.fastcampus.loan.service;

import com.fastcampus.loan.dto.JudgmentDTO;

public interface JudgmentService {

    JudgmentDTO.Response create(JudgmentDTO.Request request);
}
