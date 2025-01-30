package com.jhonny.coffee.webflux.postgresql.model.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class CoffeeSaveAllDTO {
    private List<CoffeeDTO> coffeeDTOList;
}
