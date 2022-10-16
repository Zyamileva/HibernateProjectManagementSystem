package service;

import model.dto.CustomersDto;
import model.dto.DevelopersDto;

import java.util.List;
import java.util.Optional;

public interface DeveloperService {

    DevelopersDto saveDeveloper(DevelopersDto developer);

    Optional<DevelopersDto> findById(int id);

    List<DevelopersDto> findAll();

    void delete(DevelopersDto developers);

    void deleteSkillsOfDeveloper(int idDeveloper);

    void deleteDevelopersOfIdsSkill(int idSkill);
    List<DevelopersDto> listOfSkillLevelDevelopers(String skillLevel);

    List<DevelopersDto> listOfSkillNameDevelopers(String skillName);

    void saveSkills(int idDeveloper, int idNameLevel);

    void update (DevelopersDto developer);

}
