package com.jhonny.coffee.webflux.postgresql.service.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ErrorResponse extends Response{
    private String message;

    public ErrorResponse(String message) {
        super(false);
        this.message = message;
    }
}
