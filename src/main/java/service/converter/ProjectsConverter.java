package service.converter;

import model.dao.ProjectsDao;
import model.dto.ProjectsDto;

public class ProjectsConverter implements Converter<ProjectsDto, ProjectsDao> {
    @Override
    public ProjectsDto from(ProjectsDao entity) {
        ProjectsDto projectsDto = new ProjectsDto();
        projectsDto.setId(entity.getId());
        projectsDto.setName(entity.getName());
        projectsDto.setDatePosted(entity.getDatePosted());
        projectsDto.setTask_difficulty(entity.getTask_difficulty());
        projectsDto.setCompanyId(entity.getCompanyId());
        projectsDto.setCustomerId(entity.getCustomerId());
        projectsDto.setCost(entity.getCost());
        return projectsDto;
    }

    @Override
    public ProjectsDao to(ProjectsDto entity) {
        ProjectsDao projectsDao = new ProjectsDao();
        projectsDao.setId(entity.getId());
        projectsDao.setDatePosted(entity.getDatePosted());
        projectsDao.setName(entity.getName());
        projectsDao.setTask_difficulty(entity.getTask_difficulty());
        projectsDao.setCompanyId(entity.getCompanyId());
        projectsDao.setCustomerId(entity.getCustomerId());
        projectsDao.setCost(entity.getCost());
        return projectsDao;
    }
}
