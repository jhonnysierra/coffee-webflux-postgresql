package com.jhonny.coffee.webflux.postgresql.repository;

import com.jhonny.coffee.webflux.postgresql.model.Country;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends ReactiveCrudRepository<Country, Integer> {

}
