package com.jhonny.coffee.webflux.postgresql.service;

import com.jhonny.coffee.webflux.postgresql.model.Country;
import com.jhonny.coffee.webflux.postgresql.model.dto.CountryDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICountryService {

    Mono<Country> saveCountry(CountryDTO countryDTO);

    Mono<CountryDTO> findCountryById(int id);

    Flux<CountryDTO> listAllCountry();

}
