package controller.projects;

import config.HibernateProvider;
import model.dto.ProjectsDto;
import repository.ProjectsRepository;
import service.ProjectsService;
import service.ProjectsServiceImpl;
import service.converter.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet(urlPatterns = "/projects/allProjects")
public class FindAllProjectsController extends HttpServlet {
    private ProjectsService projectsService;

    @Override
    public void init() throws ServletException {
        HibernateProvider dbProvider = new HibernateProvider();
        SkillsConverter skillsConverter = new SkillsConverter();
        CompaniesConverter companiesConverter = new CompaniesConverter(skillsConverter);
        CustomersConverter customersConverter = new CustomersConverter(skillsConverter);
        DeveloperConverter developerConverter = new DeveloperConverter(skillsConverter);
        ProjectsConverter projectsConverter = new ProjectsConverter(companiesConverter, customersConverter);
        ProjectsRepository projectsRepository = new ProjectsRepository(dbProvider);
        projectsService = new ProjectsServiceImpl(projectsRepository, developerConverter, projectsConverter);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<ProjectsDto> allProjects = projectsService.findAll();
        req.setAttribute("projects", allProjects);
        req.getRequestDispatcher("/WEB-INF/jsp/project/findAllProjects.jsp").forward(req, resp);
    }
}
