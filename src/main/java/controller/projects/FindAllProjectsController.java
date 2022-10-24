package controller.projects;

import config.DataBaseManagerConnector;
import model.dto.ProjectsDto;
import repository.ProjectsRepository;
import service.ProjectsService;
import service.ProjectsServiceImpl;
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

@WebServlet(urlPatterns = "/projects/allProjects")
public class FindAllProjectsController extends HttpServlet {
    private ProjectsService projectsService;

    @Override
    public void init() throws ServletException {
        Connection connector = DataBaseManagerConnector.getInstance().getConnector();
        DeveloperConverter developerConverter = new DeveloperConverter();
        ProjectsConverter projectsConverter = new ProjectsConverter();
        ProjectsRepository projectsRepository = new ProjectsRepository(connector);
        projectsService = new ProjectsServiceImpl(projectsRepository, developerConverter, projectsConverter);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<ProjectsDto> allProjects = projectsService.findAll();
        req.setAttribute("projects", allProjects);
        req.getRequestDispatcher("/WEB-INF/jsp/project/findAllProjects.jsp").forward(req, resp);
    }
}
