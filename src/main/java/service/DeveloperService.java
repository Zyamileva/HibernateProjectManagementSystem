package service;

import model.dto.DevelopersDto;

import java.util.List;
import java.util.Optional;

public interface DeveloperService {

    DevelopersDto saveDeveloper(DevelopersDto developer);

    Optional<DevelopersDto> findById(int id);

    List<DevelopersDto> findAll();

    //boolean existById(int id);

    void delete(DevelopersDto developers);
}

