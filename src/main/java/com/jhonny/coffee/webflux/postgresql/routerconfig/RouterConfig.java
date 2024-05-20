package com.jhonny.coffee.webflux.postgresql.routerconfig;

import com.jhonny.coffee.webflux.postgresql.controller.CoffeeHandler;
import com.jhonny.coffee.webflux.postgresql.controller.CountryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

/**
 * Clase que permite administrar las rutas de la aplicacion
 */
@Configuration
@RequiredArgsConstructor
public class RouterConfig {

    private static final String COFFEE = "/api/coffee";
    private static final String COUNTRY = "/api/country";

    private final CoffeeHandler coffeeHandler;

    private final CountryHandler countryHandler;

    /**
     * Funcion que permite asignar la ruta a una funcion del controlador
     *
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> routes() {

        return RouterFunctions.route(
                POST(COFFEE + "/save").and(accept(MediaType.APPLICATION_JSON)),
                        coffeeHandler::listenPOSTSaveCoffee)
                .andRoute(PATCH(COFFEE + "/update").and(accept(MediaType.APPLICATION_JSON)),
                        coffeeHandler::listenPATCHUpdateCoffee)
                .andRoute(GET(COFFEE + "/list/all").and(accept(MediaType.APPLICATION_JSON)),
                        coffeeHandler::listenGETListAll)
                .andRoute(GET(COFFEE + "/find/{id}").and(accept(MediaType.APPLICATION_JSON)),
                        coffeeHandler::listenGETCoffeeById)
                .andRoute(DELETE(COFFEE + "/delete/{id}").and(accept(MediaType.APPLICATION_JSON)),
                        coffeeHandler::listenDELETEDeleteCoffee)
                .andRoute(GET(COUNTRY + "/list/all").and(accept(MediaType.APPLICATION_JSON)),
                        countryHandler::listenGETListAll)
                ;
    }

}
