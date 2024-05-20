package com.jhonny.coffee.webflux.postgresql.controller;

import com.jhonny.coffee.webflux.postgresql.model.dto.CoffeeDTO;
import com.jhonny.coffee.webflux.postgresql.service.ICoffeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Log4j2
@Component
@RequiredArgsConstructor
public class CoffeeHandler {

    private final ICoffeeService iCoffeeService;

    public Mono<ServerResponse> listenPOSTSaveCoffee(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> listenPATCHUpdateCoffee(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> listenGETListAll(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(iCoffeeService.listAllCoffee(), CoffeeDTO.class);
    }

    public Mono<ServerResponse> listenGETCoffeeById(ServerRequest serverRequest) {
        int id = Integer.parseInt(serverRequest.pathVariable("id"));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(iCoffeeService.findCoffeeById(id), CoffeeDTO.class);
    }

    public Mono<ServerResponse> listenDELETEDeleteCoffee(ServerRequest serverRequest) {
        return null;
    }
}
