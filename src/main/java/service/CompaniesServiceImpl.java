package service;

import model.dao.CompaniesDao;
import model.dao.CustomersDao;
import model.dto.CompaniesDto;
import model.dto.CustomersDto;
import repository.CompaniesRepository;
import repository.CustomersRepository;
import service.converter.Converter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CompaniesServiceImpl implements CompaniesService {
    CompaniesRepository companiesRepository;
    Converter<CompaniesDto, CompaniesDao> converterCompany;

    public CompaniesServiceImpl(CompaniesRepository companiesRepository, Converter<CompaniesDto,
            CompaniesDao> converterCompany) {
        this.companiesRepository = companiesRepository;
        this.converterCompany = converterCompany;
    }

    @Override
    public CompaniesDto saveCompanies(CompaniesDto companiesDto) {
        return converterCompany.from(companiesRepository.save(converterCompany.to(companiesDto)));
    }
    @Override
    public Optional<CompaniesDto> findById(int id) {
        return companiesRepository.findById(id).map(element -> converterCompany.from(element));
    }

    @Override
    public List<CompaniesDto> findAll() {
        return companiesRepository.findAll().stream().map(element -> converterCompany.from(element))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(CompaniesDto companies) {
        companiesRepository.delete(converterCompany.to(companies));
    }
}
