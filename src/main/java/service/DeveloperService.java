package service;

import model.dto.DevelopersDto;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface DeveloperService {

    DevelopersDto saveDeveloper(DevelopersDto developer);
    public List<Integer> listSkillsOfDevelopers(int idDeveloper);
    Optional<DevelopersDto> findByName(String name);
    Optional<DevelopersDto> findById(int id);

    Set<DevelopersDto> findAll();

    void delete(DevelopersDto developers);

    void deleteSkillsOfDeveloper(int idDeveloper);

    void deleteDevelopersOfIdsSkill(int idSkill);
    List<DevelopersDto> listOfSkillLevelDevelopers(String skillLevel);

    List<DevelopersDto> listOfSkillNameDevelopers(String skillName);

    void saveSkills(int idDeveloper, int idNameLevel);

    void update (DevelopersDto developer);

}
