package service;

import model.dto.DevelopersDto;

import java.util.List;
import java.util.Optional;

public interface DeveloperService {

    DevelopersDto saveDeveloper(DevelopersDto developer);

    Optional<DevelopersDto> findById(int id);

    List<DevelopersDto> findAll();

    List<DevelopersDto> listOfJavaDevelopers();

    void delete(DevelopersDto developers);

    public List<DevelopersDto> listOfMiddleDevelopers();

    public void saveSkills(int idDeveloper, int idNameLevel);
}
