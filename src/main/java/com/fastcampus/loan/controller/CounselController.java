package com.fastcampus.loan.controller;

import com.fastcampus.loan.domain.Counsel;
import com.fastcampus.loan.dto.CounselDTO;
import com.fastcampus.loan.dto.ResponseDTO;
import com.fastcampus.loan.service.CounselServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/counsels")
public class CounselController extends AbstractController {

    private final CounselServiceImpl counselService;

    @PostMapping
    public ResponseDTO<CounselDTO.Response> create(@RequestBody CounselDTO.Request request) {
        return ok(counselService.create(request));
    }

    @GetMapping("/{counselId}")
    public ResponseDTO<CounselDTO.Response> get(@PathVariable Long counselId) {
        return ok(counselService.get(counselId));
    }
}
