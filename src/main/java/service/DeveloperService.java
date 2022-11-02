package service;

import model.dto.DevelopersDto;

import java.util.Optional;
import java.util.Set;

public interface DeveloperService {

    DevelopersDto saveDeveloper(DevelopersDto developer);

    Optional<DevelopersDto> findByName(String name);

    Optional<DevelopersDto> findById(int id);

    Set<DevelopersDto> findAll();

    void delete(DevelopersDto developers);

    void deleteDevelopersOfIdsSkill(int idSkill);

    public Set<Integer> findDevelopersOfProject(int idProject);

    void saveSkills(int idDeveloper, int idNameLevel);

    void update(DevelopersDto developer);

    public Set<DevelopersDto> findByNameSet(String name);

}
