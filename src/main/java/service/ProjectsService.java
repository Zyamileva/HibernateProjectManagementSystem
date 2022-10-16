package service;

import model.dto.DevelopersDto;
import model.dto.ProjectsDto;

import java.util.List;
import java.util.Optional;

public interface ProjectsService {

    public ProjectsDto saveProject(ProjectsDto projectsDto);

    Optional<ProjectsDto> findById(int projectId);

    List<ProjectsDto> findAll();

    public void deleteOfIdsProject(int projectId);

    public void deleteProjectOfIdsDeveloper(int developerId);

    public void deleteOfIdsDeveloperOfProject(int developerId, int projectsId);
    public boolean findByIdDeveloperIdProjects(int idDeveloper, int idProject);

    void delete(ProjectsDto projectsDto);

    int salaryOfProjects(int id);

    public List<DevelopersDto> ListDevelopersOfProjects(int id);

    public int CountDevelopersOfProjects(int id);

    public void saveDevelopers(int idDeveloper, int idProject);

    void update(ProjectsDto projects);
}