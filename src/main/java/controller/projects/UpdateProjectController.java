package controller.projects;

import config.DataBaseManagerConnector;
import model.dto.CustomersDto;
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
import java.util.Set;

@WebServlet(urlPatterns = "/projects/update")
public class UpdateProjectController extends HttpServlet {
    private CustomersService customersService;
    private ProjectsService projectsService;
    private CompaniesService companiesService;

    @Override
    public void init() throws ServletException {
        Connection connector = DataBaseManagerConnector.getInstance().getConnector();
        ProjectsConverter projectsConverter = new ProjectsConverter();
        DeveloperConverter developerConverter = new DeveloperConverter();
        ProjectsRepository projectsRepository = new ProjectsRepository(connector);
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
            project.setCompanyId(newCompanyId);
            project.setCustomerId(newCustomerId);
            projectsService.update(project);
            req.setAttribute("message", "Project: \"" + project.getName() + "\" updated");
        } else {
            req.setAttribute("message", "Project not found");
        }
        req.getRequestDispatcher("/WEB-INF/jsp/project/updatedProject.jsp").forward(req, resp);
    }
}
