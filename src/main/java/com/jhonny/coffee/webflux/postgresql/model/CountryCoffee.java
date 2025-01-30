package com.jhonny.coffee.webflux.postgresql.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "country")
public class CountryCoffee {

    @Id
    @Column("c_id")
    private int id;

    @Column("c_name")
    private String name;

    @Column("co_id")
    private int idCoffee;

    @Column("co_name")
    private String nameCoffee;

    @MappedCollection(idColumn = "co_origin_country", keyColumn = "co_id")
    private List<CoffeeResult> listCoffee;
}
