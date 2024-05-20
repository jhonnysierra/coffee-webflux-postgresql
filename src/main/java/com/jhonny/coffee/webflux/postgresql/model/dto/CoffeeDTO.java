package com.jhonny.coffee.webflux.postgresql.model.dto;

import com.jhonny.coffee.webflux.postgresql.model.Country;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CoffeeDTO {
    private int id;
    private String name;
    private Integer originCountry;
}
