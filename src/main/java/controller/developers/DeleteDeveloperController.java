package controller.developers;

import config.HibernateProvider;
import model.dto.DevelopersDto;
import model.dto.ProjectsDto;
import model.dto.SkillsDto;
import repository.DevelopersRepository;
import repository.ProjectsRepository;
import repository.SkillsRepository;
import service.*;
import service.converter.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet(urlPatterns = "/developers/delete")
public class DeleteDeveloperController extends HttpServlet {
    private DeveloperService developerService;
    private ProjectsService projectsService;
    private SkillsService skillsService;

    @Override
    public void init() throws ServletException {
        HibernateProvider dbProvider = new HibernateProvider();
        SkillsConverter skillsConverter = new SkillsConverter();
        CompaniesConverter companiesConverter = new CompaniesConverter(skillsConverter);
        CustomersConverter customersConverter = new CustomersConverter(skillsConverter);
        DeveloperConverter developerConverter = new DeveloperConverter(skillsConverter);
        SkillsRepository skillsRepository = new SkillsRepository(dbProvider);
        DevelopersRepository developersRepository = new DevelopersRepository(dbProvider);
        developerService = new DeveloperServiceImpl(developersRepository, developerConverter);
        ProjectsConverter projectsConverter = new ProjectsConverter(companiesConverter, customersConverter);
        ProjectsRepository projectsRepository = new ProjectsRepository(dbProvider);
        projectsService = new ProjectsServiceImpl(projectsRepository, developerConverter, projectsConverter);
        skillsService = new SkillsServiceImpl(skillsRepository, skillsConverter);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("developers"));
        DevelopersDto developersDto = developerService.findById(id).get();
        Set<ProjectsDto> projects = developersDto.getProjects();
        for (ProjectsDto project : projects) {
            projectsService.delete(project);
        }
        Set<SkillsDto> skills = developersDto.getSkills();
        for (SkillsDto skill : skills) {
            skillsService.delete(skill);
        }
        developerService.delete(developersDto);
        req.setAttribute("message", "Developer: \"" + developersDto.getLastName() + " "
                + developersDto.getLastName() + "\" deleted");
        req.getRequestDispatcher("/WEB-INF/jsp/developer/deletedDeveloper.jsp").forward(req, resp);
    }
}