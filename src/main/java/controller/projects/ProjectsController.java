package controller.projects;

import config.DataBaseManagerConnector;
import model.dto.ProjectsDto;
import repository.CompaniesRepository;
import repository.CustomersRepository;
import repository.ProjectsRepository;
import service.*;
import service.converter.CompaniesConverter;
import service.converter.CustomersConverter;
import service.converter.DeveloperConverter;
import service.converter.ProjectsConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(urlPatterns = "/projects")
public class ProjectsController extends HttpServlet {
    private ProjectsService projectsService;
    private CustomersService customersService;
    private CompaniesService companiesService;

    @Override
    public void init() throws ServletException {
        Connection connector = DataBaseManagerConnector.getInstance().getConnector();
        ProjectsConverter projectsConverter = new ProjectsConverter();
        DeveloperConverter developerConverter = new DeveloperConverter();
        ProjectsRepository projectsRepository = new ProjectsRepository(connector);
        projectsService = new ProjectsServiceImpl(projectsRepository, developerConverter, projectsConverter);
        CompaniesConverter companiesConverter = new CompaniesConverter();
        CompaniesRepository companiesRepository = new CompaniesRepository(connector);
        companiesService = new CompaniesServiceImpl(companiesRepository, companiesConverter);
        CustomersConverter customersConverter = new CustomersConverter();
        CustomersRepository customersRepository = new CustomersRepository(connector);
        customersService = new CustomersServiceImpl(customersRepository, customersConverter);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String projectName = req.getParameter("projectName");
        if (projectsService.findByName(projectName).isPresent()) {
            ProjectsDto project = projectsService.findByName(projectName).get();
            String nameCompany = companiesService.findById(project.getCompanyId()).get().getName();
            String nameCustomer = customersService.findById(project.getCustomerId()).get().getName();
            req.setAttribute("companies", nameCompany);
            req.setAttribute("customers", nameCustomer);
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
        ProjectsDto projectsDto = new ProjectsDto(name, task_difficulty, customerId, companyId, cost);
        projectsService.saveProject(projectsDto);
        req.getRequestDispatcher("/WEB-INF/jsp/project/savedProject.jsp").forward(req, resp);
    }
}
