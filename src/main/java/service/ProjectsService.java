package service;

import model.dto.ProjectsDto;

import java.util.List;
import java.util.Optional;

public interface ProjectsService {

    Optional<ProjectsDto> findById(int projectId);

    List<ProjectsDto> findAll();

    boolean existById(int id);

    void delete(ProjectsDto projectsDto);
}
