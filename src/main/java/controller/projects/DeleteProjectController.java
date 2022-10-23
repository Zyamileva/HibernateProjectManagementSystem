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
//        String projectName = req.getParameter("projectName");
//        Set<ProjectsDto> byName = projectsService.findByName(projectName);
//        if (!byName.isEmpty()) {
//                projectsService.deleteOfIdsProject(byName.id);
//                projectsService.delete(projectsService.findById(id).get());
//                req.setAttribute("message", "Project: \"" + pr.getName() + "\" deleted");
//            }
//        } else {
//            req.setAttribute("message", "Project not found");
//        }
//        req.getRequestDispatcher("/WEB-INF/jsp/project/deletedProject.jsp").forward(req, resp);
    }
}