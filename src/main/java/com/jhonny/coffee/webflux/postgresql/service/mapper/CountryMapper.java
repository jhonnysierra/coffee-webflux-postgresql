package com.jhonny.coffee.webflux.postgresql.service.mapper;


import com.jhonny.coffee.webflux.postgresql.model.Country;
import com.jhonny.coffee.webflux.postgresql.model.dto.CoffeeDTO;
import com.jhonny.coffee.webflux.postgresql.model.dto.CountryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    Country convertDtoToCountry(CountryDTO countryDTO);

    CountryDTO convertCountryToDto(Country country);

}
