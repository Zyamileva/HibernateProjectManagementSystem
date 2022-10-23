package controller.projects;

import config.DataBaseManagerConnector;
import model.dto.DevelopersDto;
import model.dto.ProjectsDto;
import repository.DevelopersRepository;
import repository.ProjectsRepository;
import service.DeveloperService;
import service.DeveloperServiceImpl;
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
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@WebServlet(urlPatterns = "/projects/delete/developer/form")
public class DeleteDeveloperProjectFormController extends HttpServlet {
    private DeveloperService developerService;
    private ProjectsService projectsService;

    @Override
    public void init() throws ServletException {
        Connection connector = DataBaseManagerConnector.getInstance().getConnector();
        DeveloperConverter developerConverter = new DeveloperConverter();
        DevelopersRepository developersRepository = new DevelopersRepository(connector);
        developerService = new DeveloperServiceImpl(developersRepository, developerConverter);
        ProjectsConverter projectsConverter = new ProjectsConverter();
        ProjectsRepository projectsRepository = new ProjectsRepository(connector);
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
                projectsService.deleteOfIdsDeveloperOfProject(id, idProject);
            }
        } catch (Exception ex) {
            req.setAttribute("message", "Error delete. Developer does not exist.");
            req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
        }
        req.setAttribute("message", "Developers delete");
        req.getRequestDispatcher("/WEB-INF/jsp/project/deleteProjectDeveloperForm.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<DevelopersDto> allDevelopers = developerService.findAll();
        Set<ProjectsDto> allProjects = projectsService.findAll();
        req.setAttribute("developers", allDevelopers);
        req.setAttribute("projects", allProjects);
        req.getRequestDispatcher("/WEB-INF/jsp/project/deleteProjectDeveloperFormSearch.jsp").forward(req, resp);
    }
}
