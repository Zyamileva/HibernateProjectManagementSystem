package service.converter;

import model.dao.CustomersDao;
import model.dao.ProjectsDao;
import model.dto.CustomersDto;
import model.dto.ProjectsDto;

import java.util.stream.Collectors;

public class CustomersConverter implements Converter<CustomersDto, CustomersDao> {
    SkillsConverter skillsConverter;

    public CustomersConverter(SkillsConverter skillsConverter) {
        this.skillsConverter = skillsConverter;
    }

    @Override
    public CustomersDto from(CustomersDao entity) {
        CustomersDto customersDto = new CustomersDto();
        customersDto.setId(entity.getId());
        customersDto.setName(entity.getName());
        customersDto.setContactPerson(entity.getContactPerson());
        customersDto.setPhoneNumber(entity.getPhoneNumber());
        if (entity.getProjects() != null) {
            customersDto.setProjects(entity.getProjects().stream().map(this::projectsFrom)
                    .collect(Collectors.toSet()));
        }
        return customersDto;
    }

    @Override
    public CustomersDao to(CustomersDto entity) {
        CustomersDao customersDao = new CustomersDao();
        customersDao.setId(entity.getId());
        customersDao.setName(entity.getName());
        customersDao.setContactPerson(entity.getContactPerson());
        customersDao.setPhoneNumber(entity.getPhoneNumber());
        if (entity.getProjects() != null) {
            customersDao.setProjects(entity.getProjects().stream().map(this::projectsTo)
                    .collect(Collectors.toSet()));
        }
        return customersDao;
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
