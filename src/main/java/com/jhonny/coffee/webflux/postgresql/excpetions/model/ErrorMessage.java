package com.jhonny.coffee.webflux.postgresql.excpetions.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public enum ErrorMessage {

    NOT_FOUND(404, "Not Found"),
    ERROR_DATABASE(500, "Error in BD");

    private int statusCode;
    private String message;
}
