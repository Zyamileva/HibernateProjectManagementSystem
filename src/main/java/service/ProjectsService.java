package service;

import model.dto.ProjectsDto;

import java.util.Optional;
import java.util.Set;

public interface ProjectsService {

    public ProjectsDto saveProject(ProjectsDto projectsDto);

    Optional<ProjectsDto> findByName(String name);

    Optional<ProjectsDto> findById(int id);

    Set<ProjectsDto> findAll();

    public void deleteOfIdsDeveloperOfProject(int developerId, int projectId);

    public void deleteProjectOfIdsDeveloper(int developerId);

    public void deleteOfIdsProject(int projectId);

    void delete(ProjectsDto projectsDto);

    public void saveDevelopers(int idDeveloper, int idProject);

    void update(ProjectsDto projects);
}