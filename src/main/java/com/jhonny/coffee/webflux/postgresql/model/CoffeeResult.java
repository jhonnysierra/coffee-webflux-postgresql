package com.jhonny.coffee.webflux.postgresql.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "coffee")
public class CoffeeResult {

    @Id
    @Column("co_id")
    private int id;

    @Column("co_name")
    private String name;

    @Column("co_origin_country")
    private int originCountry;

}