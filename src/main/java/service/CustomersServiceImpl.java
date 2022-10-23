package service;

import model.dao.CustomersDao;
import model.dto.CustomersDto;
import repository.CustomersRepository;
import service.converter.Converter;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomersServiceImpl implements CustomersService {
    CustomersRepository customersRepository;
    Converter<CustomersDto, CustomersDao> converterCustomer;

    public CustomersServiceImpl(CustomersRepository customersRepository, Converter<CustomersDto,
            CustomersDao> converterCustomer) {
        this.customersRepository = customersRepository;
        this.converterCustomer = converterCustomer;
    }

    @Override
    public CustomersDto saveCustomer(CustomersDto customersDto) {
        return converterCustomer.from(customersRepository.save(converterCustomer.to(customersDto)));
    }

    @Override
    public Optional<CustomersDto> findByName(String name) {
        return customersRepository.findByName(name).map(element -> converterCustomer.from(element));
    }

    @Override
    public Optional<CustomersDto> findById(int id) {
        return customersRepository.findById(id).map(element -> converterCustomer.from(element));
    }

    @Override
    public Set<CustomersDto> findAll() {
        return customersRepository.findAll().stream().map(element -> converterCustomer.from(element))
                .collect(Collectors.toSet());
    }

    @Override
    public void delete(CustomersDto customer) {
        customersRepository.delete(converterCustomer.to(customer));
    }

    @Override
    public void update(CustomersDto customers) {
        customersRepository.update(converterCustomer.to(customers));
    }
}