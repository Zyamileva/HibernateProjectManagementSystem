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
import java.util.Optional;

@WebServlet(urlPatterns = "/projects/delete")
public class DeleteProjectController extends HttpServlet {
    private ProjectsService projectsService;

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
        Optional<ProjectsDto> byName = projectsService.findByName(projectName);
        if (byName.isPresent()) {
            projectsService.deleteOfIdsProject(byName.get().getId());
            projectsService.delete(projectsService.findById(byName.get().getId()).get());
            req.setAttribute("message", "Project: \"" + byName.get().getName() + "\" deleted");
        } else {
            req.setAttribute("message", "Project not found");
        }
        req.getRequestDispatcher("/WEB-INF/jsp/project/deletedProject.jsp").forward(req, resp);
    }
}