package service;

import model.dto.CompaniesDto;
import model.dto.DevelopersDto;

import java.util.List;
import java.util.Optional;

public interface CompaniesService {

    CompaniesDto saveCompanies(CompaniesDto companiesDto);

    Optional<CompaniesDto> findById(int id);

    List<CompaniesDto> findAll();

    void delete(CompaniesDto companies);

    void update(CompaniesDto companies);
}
