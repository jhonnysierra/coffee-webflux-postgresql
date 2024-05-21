package com.jhonny.coffee.webflux.postgresql.service;

import com.jhonny.coffee.webflux.postgresql.model.dto.CoffeeDTO;
import com.jhonny.coffee.webflux.postgresql.model.dto.CoffeeDTORelation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICoffeeService {

    Mono<CoffeeDTO> saveCoffee(CoffeeDTO coffeeDTO);

    Mono<CoffeeDTO> updateCoffee(CoffeeDTO coffeeDTO);

    Mono<CoffeeDTO> findCoffeeById(int id);

    Mono<CoffeeDTORelation> findCoffeeByIdWithRelation(int id);

    Flux<CoffeeDTO> listAllCoffee();

}
