package com.jhonny.coffee.webflux.postgresql.model.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CoffeeDTO {
    private int id;
    private String name;
    private int originCountry;
}
