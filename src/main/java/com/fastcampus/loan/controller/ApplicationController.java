package com.fastcampus.loan.controller;

import com.fastcampus.loan.dto.ApplicationDTO;
import com.fastcampus.loan.dto.ResponseDTO;
import com.fastcampus.loan.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/applications")
public class ApplicationController extends AbstractController {

    private final ApplicationService applicationService;

    @PostMapping
    public ResponseDTO<ApplicationDTO.Response> create(@RequestBody ApplicationDTO.Request request) {
        return ok(applicationService.create(request));
    }

    @GetMapping("/{applicationId}")
    public ResponseDTO<ApplicationDTO.Response> get(@PathVariable("applicationId") Long applicationId) {
        return ok(applicationService.get(applicationId));
    }

    @PutMapping("/{applicationId}")
    public ResponseDTO<ApplicationDTO.Response> get(@PathVariable("applicationId") Long applicationId, @RequestBody ApplicationDTO.Request request) {
        return ok(applicationService.update(applicationId, request));
    }
    @DeleteMapping("/{applicationId}")
    public ResponseDTO<Void> delete(@PathVariable("applicationId") Long applicationId) {
        applicationService.delete(applicationId);
        return ok();
    }

    @PostMapping("/{applicationId}/terms")
    public ResponseDTO<Boolean> acceptTerms(@PathVariable Long applicationId, @RequestBody ApplicationDTO.AcceptTerms request) {
        return ok(applicationService.acceptTerms(applicationId, request));
    }


}
