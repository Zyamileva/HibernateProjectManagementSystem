package service.converter;

import model.dao.CompaniesDao;
import model.dao.ProjectsDao;
import model.dto.CompaniesDto;
import model.dto.ProjectsDto;

import java.util.Objects;
import java.util.stream.Collectors;

public class CompaniesConverter implements Converter<CompaniesDto, CompaniesDao> {
    SkillsConverter skillsConverter;

    public CompaniesConverter(SkillsConverter skillsConverter) {
        this.skillsConverter = skillsConverter;
    }

    @Override
    public CompaniesDto from(CompaniesDao entity) {
        CompaniesDto companiesDto = new CompaniesDto();
        companiesDto.setId(entity.getId());
        companiesDto.setName(entity.getName());
        companiesDto.setStaff(entity.getStaff());
        if (entity.getProjects() != null) {
            companiesDto.setProjects(entity.getProjects().stream().filter(Objects::nonNull)
                    .map(this::projectsFrom).collect(Collectors.toSet()));
        }
        return companiesDto;
    }

    @Override
    public CompaniesDao to(CompaniesDto entity) {
        CompaniesDao companiesDao = new CompaniesDao();
        companiesDao.setId(entity.getId());
        companiesDao.setName(entity.getName());
        companiesDao.setStaff(entity.getStaff());
        if (entity.getProjects() != null) {
            companiesDao.setProjects(entity.getProjects().stream()
                    .map(this::projectsTo).collect(Collectors.toSet()));
        }
        return companiesDao;
    }

    public ProjectsDto projectsFrom(ProjectsDao entity) {
        ProjectsDto projectsDto = new ProjectsDto();
        projectsDto.setId(entity.getId());
        projectsDto.setName(entity.getName());
        projectsDto.setDatePosted(entity.getDatePosted());
        projectsDto.setTask_difficulty(entity.getTask_difficulty());
        projectsDto.setCost(entity.getCost());
        return projectsDto;
    }

    public ProjectsDao projectsTo(ProjectsDto entity) {
        ProjectsDao projectsDao = new ProjectsDao();
        projectsDao.setId(entity.getId());
        projectsDao.setDatePosted(entity.getDatePosted());
        projectsDao.setName(entity.getName());
        projectsDao.setTask_difficulty(entity.getTask_difficulty());
        projectsDao.setCost(entity.getCost());
        return projectsDao;
    }
}