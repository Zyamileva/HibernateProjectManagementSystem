package service;

import model.dto.CompaniesDto;
import model.dto.CustomersDto;
import model.dto.DevelopersDto;

import java.util.List;
import java.util.Optional;

public interface CustomersService {

    CustomersDto saveCustomer(CustomersDto customersDto);

    Optional<CustomersDto> findById(int id);

    List<CustomersDto> findAll();

    void delete(CustomersDto customer);

    void update(CustomersDto customer);
}
