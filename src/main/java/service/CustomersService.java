package service;

import model.dto.CustomersDto;

import java.util.Optional;
import java.util.Set;

public interface CustomersService {

    CustomersDto saveCustomer(CustomersDto customersDto);

    Optional<CustomersDto> findByName(String name);

    Optional<CustomersDto> findById(int id);

    Set<CustomersDto> findAll();

    void delete(CustomersDto customer);

    void update(CustomersDto customer);
}
