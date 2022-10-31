package service.converter;

import model.dao.CompaniesDao;
import model.dao.CustomersDao;
import model.dao.DevelopersDao;
import model.dao.ProjectsDao;
import model.dto.CompaniesDto;
import model.dto.CustomersDto;
import model.dto.DevelopersDto;
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
//        customersDto.setProjects(entity.getProjects().stream().map(this::projectsFrom)
//                .collect(Collectors.toSet()));
        return customersDto;
    }

    @Override
    public CustomersDao to(CustomersDto entity) {
        CustomersDao customersDao = new CustomersDao();
        customersDao.setId(entity.getId());
        customersDao.setName(entity.getName());
        customersDao.setContactPerson(entity.getContactPerson());
        customersDao.setPhoneNumber(entity.getPhoneNumber());
//        customersDao.setProjects(entity.getProjects().stream().map(this::projectsTo)
//                .collect(Collectors.toSet()));
        return customersDao;
    }

    public ProjectsDto projectsFrom(ProjectsDao entity) {
        ProjectsDto projectsDto = new ProjectsDto();
        projectsDto.setId(entity.getId());
        projectsDto.setName(entity.getName());
        projectsDto.setDatePosted(entity.getDatePosted());
        projectsDto.setTask_difficulty(entity.getTask_difficulty());
        projectsDto.setCost(entity.getCost());
        projectsDto.setCompanies(this.companiesFrom(entity.getCompanies()));
        projectsDto.setCustomers(this.from(entity.getCustomers()));
        projectsDto.setDevelopers(entity.getDevelopers().stream().map(this::developersFrom)
                .collect(Collectors.toSet()));
        return projectsDto;
    }

    public ProjectsDao projectsTo(ProjectsDto entity) {
        ProjectsDao projectsDao = new ProjectsDao();
        projectsDao.setId(entity.getId());
        projectsDao.setDatePosted(entity.getDatePosted());
        projectsDao.setName(entity.getName());
        projectsDao.setTask_difficulty(entity.getTask_difficulty());
        projectsDao.setCost(entity.getCost());
        projectsDao.setCompanies(this.CompaniesTo(entity.getCompanies()));
        projectsDao.setCustomers(this.to(entity.getCustomers()));
        projectsDao.setDevelopers(entity.getDevelopers().stream().map(this::developersTo)
                .collect(Collectors.toSet()));
        return projectsDao;
    }

    public DevelopersDto developersFrom(DevelopersDao entity) {
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

    public DevelopersDao developersTo(DevelopersDto entity) {
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

    public CompaniesDto companiesFrom(CompaniesDao entity) {
        CompaniesDto companiesDto = new CompaniesDto();
        companiesDto.setId(entity.getId());
        companiesDto.setName(entity.getName());
        companiesDto.setStaff(entity.getStaff());
        companiesDto.setProjects(entity.getProjects().stream()
                .map(this::projectsFrom).collect(Collectors.toSet()));
        return companiesDto;
    }

    public CompaniesDao CompaniesTo(CompaniesDto entity) {
        CompaniesDao companiesDao = new CompaniesDao();
        companiesDao.setId(entity.getId());
        companiesDao.setName(entity.getName());
        companiesDao.setStaff(entity.getStaff());
        companiesDao.setProjects(entity.getProjects().stream()
                .map(this::projectsTo).collect(Collectors.toSet()));
        return companiesDao;
    }

}
