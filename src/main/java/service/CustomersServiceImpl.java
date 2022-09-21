package service;

import model.dao.CustomersDao;
import model.dao.SkillsDao;
import model.dto.CustomersDto;
import model.dto.SkillsDto;
import repository.CustomersRepository;
import repository.SkillsRepository;
import service.converter.Converter;

import java.util.List;
import java.util.Optional;
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
    public Optional<CustomersDto> findById(int id) {
        return customersRepository.findById(id).map(element -> converterCustomer.from(element));
    }

    @Override
    public List<CustomersDto> findAll() {
        return customersRepository.findAll().stream().map(element -> converterCustomer.from(element))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(CustomersDto customer) {
        customersRepository.delete(converterCustomer.to(customer));
    }
}
