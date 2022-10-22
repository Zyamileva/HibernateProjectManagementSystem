package controller.projects;

import config.DataBaseManagerConnector;
import model.dto.CustomersDto;
import model.dto.ProjectsDto;
import repository.CustomersRepository;
import repository.ProjectsRepository;
import service.CustomersService;
import service.CustomersServiceImpl;
import service.ProjectsService;
import service.ProjectsServiceImpl;
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
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/projects/delete")
public class DeleteProjectController extends HttpServlet {
    private CustomersService customersService;
    private ProjectsService projectsService;

    @Override
    public void init() throws ServletException {
        Connection connector = DataBaseManagerConnector.getInstance().getConnector();
        CustomersConverter customersConverter = new CustomersConverter();
        CustomersRepository customersRepository = new CustomersRepository(connector);
        customersService = new CustomersServiceImpl(customersRepository, customersConverter);
        ProjectsConverter projectsConverter = new ProjectsConverter();
        DeveloperConverter developerConverter = new DeveloperConverter();
        ProjectsRepository projectsRepository = new ProjectsRepository(connector);
        projectsService = new ProjectsServiceImpl(projectsRepository, developerConverter, projectsConverter);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerName = req.getParameter("customerName");

        if (!customersService.findByName(customerName).isEmpty()) {
            Set<ProjectsDto> projectsDtos = projectsService.findAll().stream()
                    .filter(el -> customersService.findById(el.getCompanyId())
                            .get().getName().equals(customerName)).collect(Collectors.toSet());
            for (ProjectsDto element : projectsDtos) {
                projectsService.deleteOfIdsProject(element.getId());
                projectsService.delete(element);
            }
            Set<CustomersDto> byName = customersService.findByName(customerName);
            for (CustomersDto customer : byName) {
                customersService.delete(customer);
                req.setAttribute("message", "Project: \"" + customer.getName() + "\" deleted");
            }
        } else {
            req.setAttribute("message", "Project not found");
        }
        req.getRequestDispatcher("/WEB-INF/jsp/project/deletedProject.jsp").forward(req, resp);
    }
}