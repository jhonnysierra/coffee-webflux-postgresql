package com.jhonny.coffee.webflux.postgresql.service;

import com.jhonny.coffee.webflux.postgresql.model.dto.CoffeeDTO;
import com.jhonny.coffee.webflux.postgresql.model.dto.CoffeeDTORelation;
import com.jhonny.coffee.webflux.postgresql.model.dto.CoffeeSaveAllDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ICoffeeService {

    Mono<CoffeeDTO> saveCoffee(CoffeeDTO coffeeDTO);

    Flux<CoffeeDTO> saveAllCoffee(CoffeeSaveAllDTO coffeeSaveAllDTO);

    Flux<CoffeeDTO> saveAllCoffeeList(List<CoffeeDTO> list);

    Mono<CoffeeDTO> updateCoffee(CoffeeDTO coffeeDTO);

    Mono<CoffeeDTO> findCoffeeById(int id);

    Mono<CoffeeDTORelation> findCoffeeByIdWithRelation(int id);

    Flux<CoffeeDTO> listAllCoffee();

}
