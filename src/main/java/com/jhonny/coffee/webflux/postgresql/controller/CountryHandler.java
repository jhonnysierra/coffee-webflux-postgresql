package com.jhonny.coffee.webflux.postgresql.controller;

import com.jhonny.coffee.webflux.postgresql.model.CountryCoffee;
import com.jhonny.coffee.webflux.postgresql.model.dto.CountryDTO;
import com.jhonny.coffee.webflux.postgresql.service.ICountryService;
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
public class CountryHandler {

    private final ICountryService iCountryService;

    public Mono<ServerResponse> listenPOSTSaveCountry(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> listenPATCHUpdateCountry(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> listenGETListAll(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(iCountryService.listAllCountry(), CountryDTO.class);
    }

    public Mono<ServerResponse> listenGETListAllWithCoffee(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(iCountryService.listAllCountryWithCoffee(), CountryCoffee.class);
    }

    public Mono<ServerResponse> listenGETListCountryById(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> listenDELETEDeleteCountry(ServerRequest serverRequest) {
        return null;
    }
}
