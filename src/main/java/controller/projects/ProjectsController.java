package controller.projects;

import config.HibernateProvider;
import model.dto.ProjectsDto;
import repository.CompaniesRepository;
import repository.CustomersRepository;
import repository.ProjectsRepository;
import service.*;
import service.converter.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(urlPatterns = "/projects")
public class ProjectsController extends HttpServlet {
    private ProjectsService projectsService;
    private CustomersService customersService;
    private CompaniesService companiesService;

    @Override
    public void init() throws ServletException {
        HibernateProvider dbProvider = new HibernateProvider();
        SkillsConverter skillsConverter = new SkillsConverter();
        CompaniesConverter companiesConverter = new CompaniesConverter(skillsConverter);
        CustomersConverter customersConverter = new CustomersConverter(skillsConverter);
        DeveloperConverter developerConverter = new DeveloperConverter(skillsConverter, companiesConverter, customersConverter);
        ProjectsConverter projectsConverter = new ProjectsConverter(companiesConverter, customersConverter, developerConverter);
        ProjectsRepository projectsRepository = new ProjectsRepository(dbProvider);
        projectsService = new ProjectsServiceImpl(projectsRepository, developerConverter, projectsConverter);
        CompaniesRepository companiesRepository = new CompaniesRepository(dbProvider);
        companiesService = new CompaniesServiceImpl(companiesRepository, companiesConverter);
        CustomersRepository customersRepository = new CustomersRepository(dbProvider);
        customersService = new CustomersServiceImpl(customersRepository, customersConverter);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String projectName = req.getParameter("projectName");
        if (projectsService.findByName(projectName).isPresent()) {
            ProjectsDto project = projectsService.findByName(projectName).get();
            req.setAttribute("projects", project);
        } else {
            req.setAttribute("message", "Project not found");
        }
        req.getRequestDispatcher("/WEB-INF/jsp/project/findProject.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("projectName");
        int cost = Integer.parseInt(req.getParameter("Cost"));
        String task_difficulty = req.getParameter("taskDifficulty");
        int customerId = Integer.parseInt(req.getParameter("customer"));
        int companyId = Integer.parseInt(req.getParameter("company"));
        LocalDateTime dateCreate = LocalDateTime.now();
        ProjectsDto projectsDto = new ProjectsDto(name, dateCreate, task_difficulty, cost, companiesService.findById(companyId).get(),
                customersService.findById(customerId).get());
        projectsService.saveProject(projectsDto);
        req.getRequestDispatcher("/WEB-INF/jsp/project/savedProject.jsp").forward(req, resp);
    }
}
