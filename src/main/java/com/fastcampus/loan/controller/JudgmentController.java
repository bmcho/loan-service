package com.fastcampus.loan.controller;

import com.fastcampus.loan.dto.ApplicationDTO;
import com.fastcampus.loan.dto.JudgmentDTO;
import com.fastcampus.loan.dto.ResponseDTO;
import com.fastcampus.loan.service.JudgmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/judgments")
public class JudgmentController extends AbstractController {

    private final JudgmentService judgmentService;

    @PostMapping
    public ResponseDTO<JudgmentDTO.Response> creat(@RequestBody JudgmentDTO.Request request) {
        return ok(judgmentService.create(request));
    }

    @GetMapping("/{judgmentId}")
    public ResponseDTO<JudgmentDTO.Response> get(@PathVariable("judgmentId") Long judgmentId) {
        return ok(judgmentService.get(judgmentId));
    }

    @GetMapping("/applications/{applicationId}")
    public ResponseDTO<JudgmentDTO.Response> getJudgmentOfApplication(@PathVariable("applicationId") Long applicationId) {
        return ok(judgmentService.getJudgmentOfApplication(applicationId));
    }

    @PutMapping("/{judgmentId}")
    public ResponseDTO<JudgmentDTO.Response> update(@PathVariable("judgmentId") Long judgmentId, @RequestBody JudgmentDTO.Request request) {
        return ok(judgmentService.update(judgmentId, request));
    }

    @DeleteMapping("/{judgmentId}")
    public ResponseDTO<JudgmentDTO.Response> delete(@PathVariable("judgmentId") Long judgmentId) {
        judgmentService.delete(judgmentId);
        return ok();
    }

    @PutMapping("/{judgmentId}/grants")
    public ResponseDTO<ApplicationDTO.GrantAmount> grant(@PathVariable("judgmentId") Long judgmentId) {
        return ok(judgmentService.grant(judgmentId));
    }
}
