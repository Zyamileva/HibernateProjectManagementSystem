package service;

import model.dao.DevelopersSkillsDao;
import model.dao.ProjectsDao;
import model.dto.DevelopersSkillsDto;
import model.dto.ProjectsDto;
import repository.ProjectsRepository;
import service.converter.Converter;

import java.util.List;
import java.util.Optional;

public class ProjectsServiceImpl implements ProjectsService {
    ProjectsRepository projectsRepository;
    Converter<ProjectsDto, ProjectsDao> converter;

    public ProjectsServiceImpl(ProjectsRepository projectsRepository, Converter<ProjectsDto, ProjectsDao> converter) {
        this.projectsRepository = projectsRepository;
        this.converter = converter;
    }

    @Override
    public Optional<ProjectsDto> findById(int projectId) {
        return projectsRepository.findById(projectId).map(element->converter.from(element));
    }

    @Override
    public List<ProjectsDto> findAll() {
        return null;
    }

    @Override
    public boolean existById(int id) {
        return false;
    }

    @Override
    public void delete(ProjectsDto projectsDto) {

    }

    public int sallaryOfProjects(int id) {
        return projectsRepository.sallaryOfProjects(id);
    }
}
