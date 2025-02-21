package com.jhonny.coffee.webflux.postgresql.exceptions;


import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Collections;
import java.util.Map;
@Component
public class ErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request,
                                                  ErrorAttributeOptions options) {

        Map<String, Object> map = super.getErrorAttributes(
                request, options);
        map.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        map.put("message", "Error not controled by application");
        return Collections.emptyMap();
    }
}
