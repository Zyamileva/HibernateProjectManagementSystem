package service;

import model.dto.CompaniesDto;
import model.dto.CustomersDto;
import model.dto.DevelopersDto;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CustomersService {

    CustomersDto saveCustomer(CustomersDto customersDto);

    Set<CustomersDto> findByName(String name);

    Optional<CustomersDto> findById(int id);

    Set<CustomersDto> findAll();

    void delete(CustomersDto customer);

    void update(CustomersDto customer);
}
