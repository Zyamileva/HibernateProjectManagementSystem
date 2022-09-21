package service;

import model.dto.CompaniesDto;

import java.util.List;
import java.util.Optional;

public interface CompaniesService {

    CompaniesDto saveCompanies(CompaniesDto companiesDto);

    Optional<CompaniesDto> findById(int id);

    List<CompaniesDto> findAll();

    void delete(CompaniesDto companies);
}
