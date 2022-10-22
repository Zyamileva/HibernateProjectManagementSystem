package controller.developers;

import config.DataBaseManagerConnector;
import model.dto.DevelopersDto;
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

@WebServlet(urlPatterns = "/developers/delete")
public class DeleteDeveloperController extends HttpServlet {
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
        int id = Integer.parseInt(req.getParameter("developers"));
        projectsService.deleteProjectOfIdsDeveloper(id);
        developerService.deleteSkillsOfDeveloper(id);
        DevelopersDto developersDto = developerService.findById(id).get();
        developerService.delete(developersDto);
        req.setAttribute("message", "Developer: \"" + developersDto.getLastName() + " "
                + developersDto.getLastName() + "\" deleted");
        req.getRequestDispatcher("/WEB-INF/jsp/developer/deletedDeveloper.jsp").forward(req, resp);
    }
}