package com.jhonny.coffee.webflux.postgresql.service.mapper;


import com.jhonny.coffee.webflux.postgresql.model.Coffee;
import com.jhonny.coffee.webflux.postgresql.model.dto.CoffeeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CoffeeMapper {

    @Mapping(target = "country", ignore = true)
    Coffee convertirDTOACafe(CoffeeDTO coffeeDTO);

    CoffeeDTO convertCoffeeToDTO(Coffee coffee);

}
