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

@WebServlet(urlPatterns = "/developers")
public class DeveloperController extends HttpServlet {
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
        String developerLastName = req.getParameter("developerLastName");
        Set<DevelopersDto> developers = developerService.findByName(developerLastName);
        req.setAttribute("developers", developers);
        req.getRequestDispatcher("/WEB-INF/jsp/developer/findDeveloper.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String developerFirstName = req.getParameter("developerFirstName");
        String developerLastName = req.getParameter("developerLastName");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("phoneNumber");
        int salary = Integer.parseInt(req.getParameter("salary"));
        DevelopersDto developersDto = new DevelopersDto(developerFirstName,
                developerLastName, email, phoneNumber, salary);
        developerService.saveDeveloper(developersDto);
        req.getRequestDispatcher("/WEB-INF/jsp/developer/savedDeveloper.jsp").forward(req, resp);
    }
}
