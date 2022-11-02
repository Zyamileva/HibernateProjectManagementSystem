package service;

import model.dao.DevelopersDao;
import model.dao.ProjectsDao;
import model.dto.DevelopersDto;
import model.dto.ProjectsDto;
import repository.ProjectsRepository;
import service.converter.Converter;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ProjectsServiceImpl implements ProjectsService {
    ProjectsRepository projectsRepository;
    Converter<DevelopersDto, DevelopersDao> converterDeveloper;
    Converter<ProjectsDto, ProjectsDao> converterProjects;

    public ProjectsServiceImpl(ProjectsRepository projectsRepository,
                               Converter<DevelopersDto, DevelopersDao> converterDeveloper,
                               Converter<ProjectsDto, ProjectsDao> converterProjects) {
        this.projectsRepository = projectsRepository;
        this.converterDeveloper = converterDeveloper;
        this.converterProjects = converterProjects;
    }

    @Override
    public ProjectsDto saveProject(ProjectsDto projectsDto) {
        return converterProjects.from(projectsRepository.save(converterProjects.to(projectsDto)));
    }

    @Override
    public Optional<ProjectsDto> findByName(String name) {
        return projectsRepository.findByName(name).map(element -> converterProjects.from(element));
    }

    @Override
    public Optional<ProjectsDto> findById(int projectId) {
        return projectsRepository.findById(projectId).map(element -> converterProjects.from(element));
    }

    @Override
    public Set<ProjectsDto> findAll() {
        return projectsRepository.findAll().stream().map(element -> converterProjects.from(element))
                .collect(Collectors.toSet());
    }

    @Override
    public void deleteOfIdsDeveloperOfProject(int developerId, int projectId) {
        projectsRepository.deleteOfIdsDeveloperOfProject(developerId, projectId);
    }

    @Override
    public void deleteProjectOfIdsDeveloper(int developerId) {
        projectsRepository.deleteProjectOfIdsDeveloper(developerId);
    }

    @Override
    public void deleteOfIdsProject(int projectId) {
        projectsRepository.deleteOfIdsProject(projectId);
    }

    @Override
    public void delete(ProjectsDto projectsDto) {
        projectsRepository.delete(converterProjects.to(projectsDto));
    }

    @Override
    public void saveDevelopers(int idDeveloper, int idProject) {
        projectsRepository.saveDeveloper(idDeveloper, idProject);
    }

    @Override
    public void update(ProjectsDto projects) {
        projectsRepository.update(converterProjects.to(projects));
    }
}
