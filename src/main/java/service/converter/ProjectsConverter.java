package service.converter;

import model.dao.ProjectsDao;
import model.dto.ProjectsDto;

public class ProjectsConverter implements Converter<ProjectsDto, ProjectsDao> {
    CompaniesConverter companiesConverter;
    CustomersConverter customersConverter;

    public ProjectsConverter(CompaniesConverter companiesConverter, CustomersConverter customersConverter) {
        this.companiesConverter = companiesConverter;
        this.customersConverter = customersConverter;
    }

    @Override
    public ProjectsDto from(ProjectsDao entity) {
        ProjectsDto projectsDto = new ProjectsDto();
        projectsDto.setId(entity.getId());
        projectsDto.setName(entity.getName());
        projectsDto.setDatePosted(entity.getDatePosted());
        projectsDto.setTask_difficulty(entity.getTask_difficulty());
        projectsDto.setCost(entity.getCost());
        if (entity.getCompanies() != null) {
            projectsDto.setCompanies(companiesConverter.from(entity.getCompanies()));
        }
        if (entity.getCustomers() != null) {
            projectsDto.setCustomers(customersConverter.from(entity.getCustomers()));
        }
        return projectsDto;
    }

    @Override
    public ProjectsDao to(ProjectsDto entity) {
        ProjectsDao projectsDao = new ProjectsDao();
        projectsDao.setId(entity.getId());
        projectsDao.setDatePosted(entity.getDatePosted());
        projectsDao.setName(entity.getName());
        projectsDao.setTask_difficulty(entity.getTask_difficulty());
        projectsDao.setCost(entity.getCost());
        if (entity.getCompanies() != null) {
            projectsDao.setCompanies(companiesConverter.to(entity.getCompanies()));
        }
        if (entity.getCustomers() != null) {
            projectsDao.setCustomers(customersConverter.to(entity.getCustomers()));
        }
        return projectsDao;
    }
}
