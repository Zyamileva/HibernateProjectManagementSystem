package service;

import model.dao.CompaniesDao;
import model.dto.CompaniesDto;
import repository.CompaniesRepository;
import service.converter.Converter;

import java.util.Optional;
import java.util.Set;
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
    public Optional<CompaniesDto> findByName(String name) {
        return companiesRepository.findByName(name).map(element -> converterCompany.from(element));
    }

    @Override
    public Optional<CompaniesDto> findById(int id) {
        return companiesRepository.findById(id).map(element -> converterCompany.from(element));
    }

    @Override
    public Set<CompaniesDto> findAll() {
        return companiesRepository.findAll().stream().map(element -> converterCompany.from(element))
                .collect(Collectors.toSet());
    }

    @Override
    public void delete(CompaniesDto companies) {
        companiesRepository.delete(converterCompany.to(companies));
    }

    @Override
    public void update(CompaniesDto companies) {
        companiesRepository.update(converterCompany.to(companies));
    }
}
