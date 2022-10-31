package controller.projects;

import config.HibernateProvider;
import model.dto.DevelopersDto;
import model.dto.ProjectsDto;
import repository.DevelopersRepository;
import repository.ProjectsRepository;
import service.*;
import service.converter.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@WebServlet(urlPatterns = "/projects/create/developer/form")
public class CreateDeveloperProjectFormController extends HttpServlet {
    private DeveloperService developerService;
    private ProjectsService projectsService;

    @Override
    public void init() throws ServletException {
        HibernateProvider dbProvider = new HibernateProvider();
        SkillsConverter skillsConverter = new SkillsConverter();
        CompaniesConverter companiesConverter = new CompaniesConverter(skillsConverter);
        CustomersConverter customersConverter = new CustomersConverter(skillsConverter);
        DeveloperConverter developerConverter = new DeveloperConverter(skillsConverter, companiesConverter, customersConverter);
        DevelopersRepository developersRepository = new DevelopersRepository(dbProvider);
        developerService = new DeveloperServiceImpl(developersRepository, developerConverter);
        ProjectsConverter projectsConverter = new ProjectsConverter(companiesConverter, customersConverter, developerConverter);
        ProjectsRepository projectsRepository = new ProjectsRepository(dbProvider);
        projectsService = new ProjectsServiceImpl(projectsRepository, developerConverter, projectsConverter);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idProject = Integer.parseInt(req.getParameter("projects"));
        String[] idsDeveloper = req.getParameterValues("developerId");
        List<Integer> ids = Arrays.stream(idsDeveloper)
                .map(Integer::parseInt).toList();
        try {
            for (int id : ids) {
                projectsService.saveDevelopers(id, idProject);
            }
        } catch (Exception ex) {
            req.setAttribute("message", "Error Add. Developer already exist.");
            req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
        }
        req.setAttribute("message", "Developers add");
        req.getRequestDispatcher("/WEB-INF/jsp/project/createProjectDeveloperForm.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<DevelopersDto> allDevelopers = developerService.findAll();
        Set<ProjectsDto> allProjects = projectsService.findAll();
        req.setAttribute("developers", allDevelopers);
        req.setAttribute("projects", allProjects);
        req.getRequestDispatcher("/WEB-INF/jsp/project/createProjectDeveloperFormSearch.jsp").forward(req, resp);
    }
}
