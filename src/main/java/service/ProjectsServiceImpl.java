package service;

import model.dao.DevelopersDao;
import model.dao.ProjectsDao;
import model.dto.DevelopersDto;
import model.dto.ProjectsDto;
import model.dto.SkillsDto;
import repository.ProjectsRepository;
import service.converter.Converter;

import java.util.List;
import java.util.Optional;
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
    public Optional<ProjectsDto> findById(int projectId) {
        return projectsRepository.findById(projectId).map(element -> converterProjects.from(element));
    }

    @Override
    public List<ProjectsDto> findAll() {
        return projectsRepository.findAll().stream().map(element -> converterProjects.from(element)).collect(Collectors.toList());
    }

    @Override
    public void deleteOfIdsProject(int projectId) {
        projectsRepository.deleteOfIdsProject(projectId);
    }

    @Override
    public void deleteOfIdsDeveloper(int developerId) {
        projectsRepository.deleteOfIdsDeveloper(developerId);
    }

    @Override
    public void delete(ProjectsDto projectsDto) {
        projectsRepository.delete(converterProjects.to(projectsDto));
    }

    @Override
    public int sallaryOfProjects(int id) {
        return projectsRepository.sallaryOfProjects(id);
    }

    @Override
    public List<DevelopersDto> ListDevelopersOfProjects(int id) {
        return projectsRepository.ListDevelopersOfProjects(id).stream().map(element -> converterDeveloper.from(element))
                .collect(Collectors.toList());
    }

    @Override
    public int CountDevelopersOfProjects(int id) {
        return projectsRepository.CountDevelopersOfProjects(id);
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
