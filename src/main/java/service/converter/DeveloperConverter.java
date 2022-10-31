package service.converter;

import model.dao.DevelopersDao;
import model.dao.ProjectsDao;
import model.dto.DevelopersDto;
import model.dto.ProjectsDto;

import java.util.Set;
import java.util.stream.Collectors;

public class DeveloperConverter implements Converter<DevelopersDto, DevelopersDao> {
    SkillsConverter skillsConverter;
    CompaniesConverter companiesConverter;
    CustomersConverter customersConverter;

    public DeveloperConverter(SkillsConverter skillsConverter,
                              CompaniesConverter companiesConverter, CustomersConverter customersConverter) {
        this.skillsConverter = skillsConverter;
        this.companiesConverter = companiesConverter;
        this.customersConverter = customersConverter;
    }

    @Override
    public DevelopersDto from(DevelopersDao entity) {
        DevelopersDto developersDto = new DevelopersDto();
        developersDto.setId(entity.getId());
        developersDto.setFirstName(entity.getFirstName());
        developersDto.setLastName(entity.getLastName());
        developersDto.setEmail(entity.getEmail());
        developersDto.setPhoneNumber(entity.getPhoneNumber());
        developersDto.setSalary(entity.getSalary());
        developersDto.setProjects(entity.getProjects().stream().map(this::projectsFrom)
                .collect(Collectors.toSet()));
        developersDto.setSkills(entity.getSkills().stream().map(skillsConverter::from)
                .collect(Collectors.toSet()));
        return developersDto;
    }

    @Override
    public DevelopersDao to(DevelopersDto entity) {
        DevelopersDao developersDao = new DevelopersDao();
        developersDao.setId((entity.getId()));
        developersDao.setFirstName(entity.getFirstName());
        developersDao.setLastName(entity.getLastName());
        developersDao.setEmail(entity.getEmail());
        developersDao.setPhoneNumber(entity.getPhoneNumber());
        developersDao.setSalary(entity.getSalary());
        developersDao.setProjects(entity.getProjects().stream().map(this::projectsTo)
                .collect(Collectors.toSet()));
        developersDao.setSkills(entity.getSkills().stream().map(skillsConverter::to)
                .collect(Collectors.toSet()));
        return developersDao;
    }

    public ProjectsDto projectsFrom(ProjectsDao entity) {
        ProjectsDto projectsDto = new ProjectsDto();
        projectsDto.setId(entity.getId());
        projectsDto.setName(entity.getName());
        projectsDto.setDatePosted(entity.getDatePosted());
        projectsDto.setTask_difficulty(entity.getTask_difficulty());
        projectsDto.setCost(entity.getCost());
//        projectsDto.setCompanies(this.from(entity.getCompanies()));
//        projectsDto.setCustomers(this.customersFrom(entity.getCustomers()));
//        if (entity.getDevelopers() != null) {
//            projectsDto.setDevelopers(entity.getDevelopers().stream().map(this::developersFrom)
//                    .collect(Collectors.toSet()));
//        }
        return projectsDto;
    }

    public ProjectsDao projectsTo(ProjectsDto entity) {
        ProjectsDao projectsDao = new ProjectsDao();
        projectsDao.setId(entity.getId());
        projectsDao.setDatePosted(entity.getDatePosted());
        projectsDao.setName(entity.getName());
        projectsDao.setTask_difficulty(entity.getTask_difficulty());
        projectsDao.setCost(entity.getCost());
//        projectsDao.setCompanies(this.to(entity.getCompanies()));
//        projectsDao.setCustomers(this.customersTo(entity.getCustomers()));
//        if (entity.getDevelopers() != null) {
//            projectsDao.setDevelopers(entity.getDevelopers().stream().map(this::developersTo)
//                    .collect(Collectors.toSet()));
//        }
        return projectsDao;
    }

}
