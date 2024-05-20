package com.jhonny.coffee.webflux.postgresql.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "coffee")
public class Coffee {
    @Id
    @Column("id")
    private int id;

    @Column("name")
    private String name;

    @Column("origin_country")
    private Integer originCountry;

    @Transient
    private Country country;
}
