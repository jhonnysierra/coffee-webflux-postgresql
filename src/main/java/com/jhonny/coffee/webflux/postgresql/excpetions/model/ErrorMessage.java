package com.jhonny.coffee.webflux.postgresql.excpetions.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public enum ErrorMessage {

    NOT_FOUND(404, "Not Found");

    private int statusCode;
    private String message;
}
