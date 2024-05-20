package com.jhonny.coffee.webflux.postgresql.repository;


import com.jhonny.coffee.webflux.postgresql.model.Coffee;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeRepository extends ReactiveCrudRepository<Coffee, Integer> {
}
