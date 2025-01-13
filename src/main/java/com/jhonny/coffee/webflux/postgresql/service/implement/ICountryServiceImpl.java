package com.jhonny.coffee.webflux.postgresql.service.implement;

import com.jhonny.coffee.webflux.postgresql.model.Country;
import com.jhonny.coffee.webflux.postgresql.model.CountryCoffee;
import com.jhonny.coffee.webflux.postgresql.model.dto.CountryDTO;
import com.jhonny.coffee.webflux.postgresql.repository.CountryCoffeeRepository;
import com.jhonny.coffee.webflux.postgresql.repository.CountryRepository;
import com.jhonny.coffee.webflux.postgresql.service.ICountryService;
import com.jhonny.coffee.webflux.postgresql.service.mapper.CountryMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ICountryServiceImpl implements ICountryService {

    private final CountryRepository countryRepository;
    private final CountryCoffeeRepository countryCoffeeRepository;
    private final CountryMapper countryMapper;
    private static final Logger logger = LoggerFactory.getLogger(ICountryServiceImpl.class);


    @Override
    public Mono<Country> saveCountry(CountryDTO countryDTO) {
        Country country = countryMapper.convertDtoToCountry(countryDTO);
        return countryRepository.save(country);
    }

    @Override
    public Mono<CountryDTO> findCountryById(int id) {

        Mono<Country> countryMono = countryRepository.findById(id);
        return countryMono.flatMap(result -> {
            final CountryDTO countryDTO = new CountryDTO();
            countryDTO.setId(result.getId());
            countryDTO.setName(result.getName());
            return Mono.just(countryDTO);
        });
    }

    @Override
    public Flux<CountryDTO> listAllCountry() {
        return countryRepository.findAll()
                .map(countryMapper::convertCountryToDto);
    }

    @Override
    public Flux<CountryCoffee> listAllCountryWithCoffee() {
        return countryCoffeeRepository.findAllWithCoffee()
                .doOnNext(countryCoffee -> logger.info("Country - Coffee {}", countryCoffee.toString()));
    }

}