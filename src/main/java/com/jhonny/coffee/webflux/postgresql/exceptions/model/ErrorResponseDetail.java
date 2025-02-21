package com.jhonny.coffee.webflux.postgresql.exceptions.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDetail {

    private String code;
    private String message;
    private String domain;
}