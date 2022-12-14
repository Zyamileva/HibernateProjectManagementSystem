package controller.projects;

import config.HibernateProvider;
import model.dto.DevelopersDto;
import model.dto.ProjectsDto;
import repository.DevelopersRepository;
import repository.ProjectsRepository;
import service.DeveloperService;
import service.DeveloperServiceImpl;
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
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/projects/delete/developer/form")
public class DeleteDeveloperProjectFormController extends HttpServlet {
    private DeveloperService developerService;
    private ProjectsService projectsService;

    @Override
    public void init() throws ServletException {
        HibernateProvider dbProvider = new HibernateProvider();
        SkillsConverter skillsConverter = new SkillsConverter();
        CompaniesConverter companiesConverter = new CompaniesConverter(skillsConverter);
        CustomersConverter customersConverter = new CustomersConverter(skillsConverter);
        DeveloperConverter developerConverter = new DeveloperConverter(skillsConverter);
        DevelopersRepository developersRepository = new DevelopersRepository(dbProvider);
        developerService = new DeveloperServiceImpl(developersRepository, developerConverter);
        ProjectsConverter projectsConverter = new ProjectsConverter(companiesConverter, customersConverter);
        ProjectsRepository projectsRepository = new ProjectsRepository(dbProvider);
        projectsService = new ProjectsServiceImpl(projectsRepository, developerConverter, projectsConverter);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("projects"));
        Set<DevelopersDto> developers = developerService.findDevelopersOfProject(id).stream()
                .map(el -> developerService.findById(el).get()).collect(Collectors.toSet());
        req.setAttribute("project", projectsService.findById(id).get().getName());
        req.setAttribute("developers", developers);
        req.getRequestDispatcher("/WEB-INF/jsp/project/deleteProjectDeveloper.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<ProjectsDto> allProjects = projectsService.findAll();
        req.setAttribute("projects", allProjects);
        req.getRequestDispatcher("/WEB-INF/jsp/project/deleteProjectDeveloperFormSearch.jsp").forward(req, resp);
    }
}
