package com.jhonny.coffee.webflux.postgresql.controller;

import com.jhonny.coffee.webflux.postgresql.model.dto.CoffeeDTO;
import com.jhonny.coffee.webflux.postgresql.model.dto.CoffeeDTORelation;
import com.jhonny.coffee.webflux.postgresql.model.dto.CoffeeSaveAllDTO;
import com.jhonny.coffee.webflux.postgresql.service.ICoffeeService;
import com.jhonny.coffee.webflux.postgresql.service.mapper.CoffeeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Component
@RequiredArgsConstructor
public class CoffeeHandler {

    private final ICoffeeService iCoffeeService;
    private static final Logger logger = LoggerFactory.getLogger(CoffeeHandler.class);

    private final CoffeeMapper coffeeMapper;

    public Mono<ServerResponse> listenPOSTSaveCoffee(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(CoffeeDTO.class)
                .flatMap(coffeeDTO ->
                        ServerResponse
                                .ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(iCoffeeService.saveCoffee(coffeeDTO), CoffeeDTO.class));
    }

    public Mono<ServerResponse> listenPOSTSaveAllCoffee(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(CoffeeSaveAllDTO.class)
                .flatMap(coffeeSaveAllDTO ->
                        ServerResponse
                                .ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(iCoffeeService.saveAllCoffee(coffeeSaveAllDTO), CoffeeSaveAllDTO.class));
    }
    public Mono<ServerResponse> listenPOSTSaveAllCoffeeList(ServerRequest serverRequest) {
        List<CoffeeDTO> coffeeDTOList = new ArrayList<>();

        return serverRequest.bodyToMono(List.class)
                .flatMap(list ->
                        ServerResponse
                                .ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(iCoffeeService.saveAllCoffeeList(list), List.class));
    }
    public Mono<ServerResponse> listenPATCHUpdateCoffee(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(CoffeeDTO.class)
                .flatMap(coffeeDTO ->
                        ServerResponse
                                .ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(iCoffeeService.updateCoffee(coffeeDTO), CoffeeDTO.class));
    }
    public Mono<ServerResponse> listenGETListAll() {
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

    public Mono<ServerResponse> listenGETCoffeeByIdWithRelation(ServerRequest serverRequest) {
        int id = Integer.parseInt(serverRequest.pathVariable("id"));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(iCoffeeService.findCoffeeByIdWithRelation(id), CoffeeDTORelation.class);
    }

    public Mono<ServerResponse> listenDELETEDeleteCoffee(ServerRequest serverRequest) {
        return null;
    }

}
