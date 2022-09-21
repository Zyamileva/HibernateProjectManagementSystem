package service;

import model.dto.DevelopersDto;
import model.dto.ProjectsDto;
import model.dto.SkillsDto;

import java.util.List;
import java.util.Optional;

public interface ProjectsService {

    public ProjectsDto saveProject(ProjectsDto projectsDto);

    Optional<ProjectsDto> findById(int projectId);

    List<ProjectsDto> findAll();

    void delete(ProjectsDto projectsDto);

    int sallaryOfProjects(int id);

    public List<DevelopersDto> ListDevelopersOfProjects(int id);

    public int CountDevelopersOfProjects(int id);
}
