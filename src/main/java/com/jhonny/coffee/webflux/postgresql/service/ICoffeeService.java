package com.jhonny.coffee.webflux.postgresql.service;

import com.jhonny.coffee.webflux.postgresql.model.Coffee;
import com.jhonny.coffee.webflux.postgresql.model.dto.CoffeeDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICoffeeService {

    Mono<Coffee> saveCoffee(CoffeeDTO coffeeDTO);

    Mono<CoffeeDTO> findCoffeeById(int id);

    Flux<CoffeeDTO> listAllCoffee();

}
