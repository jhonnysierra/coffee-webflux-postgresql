package com.jhonny.coffee.webflux.postgresql.service.implement;

import com.jhonny.coffee.webflux.postgresql.model.Coffee;
import com.jhonny.coffee.webflux.postgresql.model.Country;
import com.jhonny.coffee.webflux.postgresql.model.dto.CoffeeDTO;
import com.jhonny.coffee.webflux.postgresql.model.dto.CountryDTO;
import com.jhonny.coffee.webflux.postgresql.repository.CountryRepository;
import com.jhonny.coffee.webflux.postgresql.service.ICountryService;
import com.jhonny.coffee.webflux.postgresql.service.mapper.CountryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ICountryServiceImpl implements ICountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

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

}