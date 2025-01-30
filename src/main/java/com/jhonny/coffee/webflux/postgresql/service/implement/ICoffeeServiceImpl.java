package com.jhonny.coffee.webflux.postgresql.service.implement;

import com.jhonny.coffee.webflux.postgresql.excpetions.model.DomainException;
import com.jhonny.coffee.webflux.postgresql.excpetions.model.ErrorMessage;
import com.jhonny.coffee.webflux.postgresql.model.Coffee;
import com.jhonny.coffee.webflux.postgresql.model.dto.CoffeeDTO;
import com.jhonny.coffee.webflux.postgresql.model.dto.CoffeeDTORelation;
import com.jhonny.coffee.webflux.postgresql.model.dto.CoffeeSaveAllDTO;
import com.jhonny.coffee.webflux.postgresql.repository.CoffeeRepository;
import com.jhonny.coffee.webflux.postgresql.repository.CountryRepository;
import com.jhonny.coffee.webflux.postgresql.service.ICoffeeService;
import com.jhonny.coffee.webflux.postgresql.service.mapper.CoffeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Service
public class ICoffeeServiceImpl implements ICoffeeService {

    private final CoffeeRepository coffeeRepository;
    private final CountryRepository countryRepository;
    private final CoffeeMapper coffeeMapper;

    private static final Logger logger = LoggerFactory.getLogger(ICoffeeServiceImpl.class);

    public ICoffeeServiceImpl(CoffeeRepository coffeeRepository, CountryRepository countryRepository,
                              CoffeeMapper coffeeMapper) {
        this.coffeeRepository = coffeeRepository;
        this.countryRepository = countryRepository;
        this.coffeeMapper = coffeeMapper;
    }

    @Override
    public Mono<CoffeeDTO> saveCoffee(CoffeeDTO coffeeDTO) {
        Coffee coffee = coffeeMapper.convertDTOToCoffee(coffeeDTO);
        //Para este caso se guarda el objeto sin validar que exista antes. No se envía el id en el body del request
        /*return coffeeRepository.save(coffee)
                .doOnSuccess(coffee2 -> logger.info("Coffee Saved {}", coffee2))
                .map(coffeeMapper::convertCoffeeToDTO);*/

        //Implementacion cuando se envía el id en el objeto json. Se debe enviar el id en el body del request
        return coffeeRepository.findById(coffee.getId())
                .filter(Objects::isNull)
                .flatMap(coffee1 ->
                        coffeeRepository.save(coffee1)
                                .doOnSuccess(coffee2 -> logger.info("Coffee Saved {}", coffee2)))
                .map(coffeeMapper::convertCoffeeToDTO)
                .switchIfEmpty(Mono.error(new Throwable("The coffee already exists")));
    }

    @Override
    public Flux<CoffeeDTO> saveAllCoffee(CoffeeSaveAllDTO coffeeSaveAllDTO) {
        List<Coffee> coffeeList = coffeeMapper.convertListDtoToCoffee(coffeeSaveAllDTO.getCoffeeDTOList());
        return coffeeRepository.saveAll(coffeeList)
                .map(coffeeMapper::convertCoffeeToDTO);
    }

    @Override
    public Flux<CoffeeDTO> saveAllCoffeeList(List<CoffeeDTO> list) {
        List<Coffee> coffeeList = coffeeMapper.convertListDtoToCoffee(list);
        System.out.println("Ingresó a crear cafe con una lista");
        return coffeeRepository.saveAll(coffeeList)
                .map(coffeeMapper::convertCoffeeToDTO)
                .map(coffeeDTO -> {
                    System.out.println("Resultado despues de insert lista: " + coffeeDTO);
                    return coffeeDTO;
                });
    }

    @Override
    public Mono<CoffeeDTO> updateCoffee(CoffeeDTO coffeeDTO) {
        Coffee coffee = coffeeMapper.convertDTOToCoffee(coffeeDTO);
        logger.info("Entry to service implementation {}", coffeeDTO);
        return coffeeRepository.findById(coffee.getId())
                .filter(Objects::nonNull)
                .doOnError(throwable -> logger.error("Error find coffee {}", throwable.getMessage()))
                .flatMap(coffee1 ->
                    coffeeRepository.save(coffee)
                            .doOnSuccess(coffee2 -> logger.info("Coffee Updated {}", coffee2)))
                .map(coffeeMapper::convertCoffeeToDTO)
                .switchIfEmpty(Mono.error(new Throwable("The coffee not exists")));
    }

    @Override
    public Mono<CoffeeDTO> findCoffeeById(int id) {
        logger.info("Entry to service implementation findCoffeeById = {}", id);
        return coffeeRepository.findById(id)
                .map(coffeeMapper::convertCoffeeToDTO)
                .switchIfEmpty(Mono.error(new DomainException(String.format("Coffee not found. ID: %s", id), ErrorMessage.NOT_FOUND)))
                .doOnError(throwable -> logger.error(throwable.getMessage()));
    }

    @Override
    public Mono<CoffeeDTORelation> findCoffeeByIdWithRelation(int id) {
        logger.info("Entry to service implementation findCoffeeByIdWithRelation =  {}", id);
        return coffeeRepository.findById(id)
                //.map(coffeeMapper::convertCoffeeToDTO)
                .flatMap(coffee ->
                        countryRepository.findById(coffee.getOriginCountry())
                                .map(country -> {
                                    coffee.setCountry(country);
                                    return coffee;
                                }))
                .map(coffeeMapper::convertDTOACafeAllFields);
    }

    @Override
    public Flux<CoffeeDTO> listAllCoffee() {
        return coffeeRepository.findAll()
                .map(coffeeMapper::convertCoffeeToDTO);
    }

}