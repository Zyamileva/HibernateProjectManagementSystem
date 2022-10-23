package service;

import model.dto.CompaniesDto;

import java.util.Optional;
import java.util.Set;

public interface CompaniesService {

    CompaniesDto saveCompanies(CompaniesDto companiesDto);

    Optional<CompaniesDto> findByName(String name);

    Optional<CompaniesDto> findById(int id);

    Set<CompaniesDto> findAll();

    void delete(CompaniesDto companies);

    void update(CompaniesDto companies);
}
