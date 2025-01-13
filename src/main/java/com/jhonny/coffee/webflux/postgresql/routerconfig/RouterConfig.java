package com.jhonny.coffee.webflux.postgresql.routerconfig;

import com.jhonny.coffee.webflux.postgresql.controller.CoffeeHandler;
import com.jhonny.coffee.webflux.postgresql.controller.CountryHandler;
import com.jhonny.coffee.webflux.postgresql.service.ICoffeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @Bean
    @RouterOperations({
            @RouterOperation(path = COFFEE + "/list/all", method = RequestMethod.GET, beanClass = ICoffeeService.class, beanMethod = "listAllCoffee"),
            @RouterOperation(path = COFFEE + "/find/{id}", method = RequestMethod.GET, beanClass = ICoffeeService.class, beanMethod = "findCoffeeById",
                    operation = @Operation(operationId = "findCoffeeById", summary = "Fetch coffee by Id", description = "Get Coffee info by id",
                            parameters = {@Parameter(in = ParameterIn.PATH, name = "id")}))
    })
    public RouterFunction<ServerResponse> routes() {
        return RouterFunctions.route(
                POST(COFFEE + "/save").and(accept(MediaType.APPLICATION_JSON)),
                        coffeeHandler::listenPOSTSaveCoffee)
                .andRoute(PATCH(COFFEE + "/update").and(accept(MediaType.APPLICATION_JSON)),
                        coffeeHandler::listenPATCHUpdateCoffee)
                .andRoute(GET(COFFEE + "/list/all").and(accept(MediaType.APPLICATION_JSON)),
                        serverRequest -> coffeeHandler.listenGETListAll())
                .andRoute(GET(COFFEE + "/find/{id}").and(accept(MediaType.APPLICATION_JSON)),
                        coffeeHandler::listenGETCoffeeById)
                /*
                  Para usar functional DSL se debe comenta la linea anterior y se habilita esta.
                  Tambien se deberia eliminar la configuración por anotaciones
                 */
                /*.and(route().GET(COFFEE + "/find/{id}", coffeeHandler::listenGETCoffeeById, findCoffeeByIdOpenAPI()).build())*/
                .andRoute(GET(COFFEE + "/find/relation/{id}").and(accept(MediaType.APPLICATION_JSON)),
                coffeeHandler::listenGETCoffeeByIdWithRelation)
                .andRoute(DELETE(COFFEE + "/delete/{id}").and(accept(MediaType.APPLICATION_JSON)),
                        coffeeHandler::listenDELETEDeleteCoffee)
                .andRoute(GET(COUNTRY + "/list/all").and(accept(MediaType.APPLICATION_JSON)),
                        countryHandler::listenGETListAll)
                .andRoute(GET(COUNTRY + "/list/all/country/coffee").and(accept(MediaType.APPLICATION_JSON)),
                        countryHandler::listenGETListAllWithCoffee)
                ;


    }
/*  private Consumer<Builder> findCoffeeByIdOpenAPI() {
        return ops -> ops.tag("coffee")
                .operationId("findCoffeeById").summary("Find purchase order by ID").tags(new String[] { "MyCoffee" })
                .parameter(parameterBuilder().in(ParameterIn.PATH).name("id").description("Coffee Id"))
                .response(responseBuilder().responseCode("200").description("successful operation").implementation(CoffeeDTO.class))
                .response(responseBuilder().responseCode("400").description("Invalid Coffee ID supplied"))
                .response(responseBuilder().responseCode("404").description("Coffee not found"));
    }*/

}
