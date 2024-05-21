package com.jhonny.coffee.webflux.postgresql.service.mapper;


import com.jhonny.coffee.webflux.postgresql.model.Coffee;
import com.jhonny.coffee.webflux.postgresql.model.dto.CoffeeDTO;
import com.jhonny.coffee.webflux.postgresql.model.dto.CoffeeDTORelation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CoffeeMapper {

    @Mapping(target = "country", ignore = true)
    Coffee convertDTOToCoffee(CoffeeDTO coffeeDTO);

    CoffeeDTO convertCoffeeToDTO(Coffee coffee);
    @Mapping(target = "originCountry", ignore = true)
    Coffee convertDTOACafeAllFields(CoffeeDTORelation coffeeDTORelation);

    CoffeeDTORelation convertDTOACafeAllFields(Coffee coffee);
}
