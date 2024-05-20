package com.jhonny.coffee.webflux.postgresql.service.implement;

import com.jhonny.coffee.webflux.postgresql.model.Coffee;
import com.jhonny.coffee.webflux.postgresql.model.dto.CoffeeDTO;
import com.jhonny.coffee.webflux.postgresql.repository.CoffeeRepository;
import com.jhonny.coffee.webflux.postgresql.service.ICoffeeService;
import com.jhonny.coffee.webflux.postgresql.service.mapper.CoffeeMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ICoffeeServiceImpl implements ICoffeeService {

    private final CoffeeRepository coffeeRepository;
    private final CoffeeMapper coffeeMapper;

    public ICoffeeServiceImpl(CoffeeRepository coffeeRepository, CoffeeMapper coffeeMapper) {
        this.coffeeRepository = coffeeRepository;
        this.coffeeMapper = coffeeMapper;
    }

    @Override
    public Mono<Coffee> saveCoffee(CoffeeDTO coffeeDTO) {
        Coffee coffee = coffeeMapper.convertirDTOACafe(coffeeDTO);
        return coffeeRepository.save(coffee);
    }

    @Override
    public Mono<CoffeeDTO> findCoffeeById(int id) {
        return coffeeRepository.findById(id)
                .map(coffeeMapper::convertCoffeeToDTO);
    }

    @Override
    public Flux<CoffeeDTO> listAllCoffee() {
        return coffeeRepository.findAll()
                .map(coffeeMapper::convertCoffeeToDTO);
    }

}