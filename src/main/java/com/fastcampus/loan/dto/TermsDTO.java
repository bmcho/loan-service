package com.fastcampus.loan.dto;

import lombok.*;

import java.time.LocalDateTime;

public class TermsDTO {

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Request {

        private String name;
        private String termsDetailUrl;

    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Response {

        private Long termsId;
        private String name;
        private String termsDetailUrl;

        private LocalDateTime cratedAt;
        private LocalDateTime updatedAt;

    }

}
