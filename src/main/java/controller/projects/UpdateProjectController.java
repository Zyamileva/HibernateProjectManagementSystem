package controller.projects;

import config.HibernateProvider;
import model.dto.CompaniesDto;
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

@WebServlet(urlPatterns = "/projects/update")
public class UpdateProjectController extends HttpServlet {
    private CustomersService customersService;
    private ProjectsService projectsService;
    private CompaniesService companiesService;

    @Override
    public void init() throws ServletException {
        HibernateProvider dbProvider = new HibernateProvider();
        SkillsConverter skillsConverter = new SkillsConverter();
        CompaniesConverter companiesConverter = new CompaniesConverter(skillsConverter);
        CustomersConverter customersConverter = new CustomersConverter(skillsConverter);
        DeveloperConverter developerConverter = new DeveloperConverter(skillsConverter);
        ProjectsConverter projectsConverter = new ProjectsConverter(companiesConverter, customersConverter);
        ProjectsRepository projectsRepository = new ProjectsRepository(dbProvider);
        CompaniesRepository companiesRepository = new CompaniesRepository(dbProvider);
        CustomersRepository customersRepository = new CustomersRepository(dbProvider);
        companiesService = new CompaniesServiceImpl(companiesRepository, companiesConverter);
        customersService = new CustomersServiceImpl(customersRepository, customersConverter);
        projectsService = new ProjectsServiceImpl(projectsRepository, developerConverter, projectsConverter);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String projectName = req.getParameter("projectName");
        if (projectsService.findByName(projectName).isPresent()) {
            String newName = req.getParameter("projectNewName");
            String newTaskDifficulty = req.getParameter("taskDifficulty");
            int newCost = Integer.parseInt(req.getParameter("Cost"));
            int newCompanyId = Integer.parseInt(req.getParameter("newCompany"));
            int newCustomerId = Integer.parseInt(req.getParameter("newCustomer"));
            ProjectsDto project = projectsService.findByName(projectName).get();
            project.setName(newName);
            project.setTask_difficulty(newTaskDifficulty);
            project.setCost(newCost);
            CompaniesDto companiesDto = companiesService.findById(newCompanyId).get();
            project.setCompanies(companiesService.findById(newCompanyId).get());
            project.setCustomers(customersService.findById(newCustomerId).get());
            projectsService.update(project);
            req.setAttribute("message", "Project: \"" + project.getName() + "\" updated");
        } else {
            req.setAttribute("message", "Project not found");
        }
        req.getRequestDispatcher("/WEB-INF/jsp/project/updatedProject.jsp").forward(req, resp);
    }
}
