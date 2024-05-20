package com.jhonny.coffee.webflux.postgresql.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table(name = "country")
public class Country {

    @Id
    @Column("id")
    private int id;

    @Column("name")
    private String name;
}
