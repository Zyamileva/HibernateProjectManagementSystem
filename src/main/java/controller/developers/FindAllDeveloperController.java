package controller.developers;

import config.DataBaseManagerConnector;
import model.dto.DevelopersDto;
import repository.DevelopersRepository;
import service.DeveloperService;
import service.DeveloperServiceImpl;
import service.converter.DeveloperConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Set;

@WebServlet(urlPatterns = "/developers/allDevelopers")
public class FindAllDeveloperController extends HttpServlet {
    private DeveloperService developerService;

    @Override
    public void init() throws ServletException {
        Connection connector = DataBaseManagerConnector.getInstance().getConnector();
        DeveloperConverter developerConverter = new DeveloperConverter();
        DevelopersRepository developersRepository = new DevelopersRepository(connector);
        developerService = new DeveloperServiceImpl(developersRepository, developerConverter);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<DevelopersDto> allDevelopers = developerService.findAll();
        req.setAttribute("developers", allDevelopers);
        req.getRequestDispatcher("/WEB-INF/jsp/developer/findAllDevelopers.jsp").forward(req, resp);
    }
}
