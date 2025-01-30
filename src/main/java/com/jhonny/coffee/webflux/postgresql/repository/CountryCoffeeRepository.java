package com.jhonny.coffee.webflux.postgresql.repository;

import com.jhonny.coffee.webflux.postgresql.model.CountryCoffee;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CountryCoffeeRepository extends ReactiveCrudRepository<CountryCoffee, Integer> {

    @Query("""
            SELECT c.id as c_id, c.name c_name, co.id as co_id, co.name as co_name
            FROM country c 
            INNER JOIN coffee co 
                ON c.id = co.origin_country
    """)
    /*@Query("""
            SELECT c.*, co.*
            FROM country c 
            INNER JOIN coffee co 
                ON c.id = co.origin_country
    """)*/
    Flux<CountryCoffee> findAllWithCoffee();
}
