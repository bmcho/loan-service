package com.fastcampus.loan.dto;


import lombok.*;

import java.time.LocalDateTime;

public class CounselDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        private String name;
        private String cellPhone;
        private String email;
        private String memo;
        private String address;
        private String addressDetail;
        private String zipCode;

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private Long counselId;
        private String name;
        private String cellPhone;
        private String email;
        private String memo;
        private String address;
        private String addressDetail;
        private String zipCode;

        private LocalDateTime appliedAt;
        private LocalDateTime cratedAt;
        private LocalDateTime updatedAt;
    }
}
