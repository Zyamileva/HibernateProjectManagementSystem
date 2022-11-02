package controller.developers;

import config.HibernateProvider;
import model.dto.DevelopersDto;
import repository.DevelopersRepository;
import service.DeveloperService;
import service.DeveloperServiceImpl;
import service.converter.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/developers/update")
public class UpdateDeveloperController extends HttpServlet {
    private DeveloperService developerService;

    @Override
    public void init() throws ServletException {
        HibernateProvider dbProvider = new HibernateProvider();
        SkillsConverter skillsConverter = new SkillsConverter();
        DeveloperConverter developerConverter = new DeveloperConverter(skillsConverter);
        DevelopersRepository developersRepository = new DevelopersRepository(dbProvider);
        developerService = new DeveloperServiceImpl(developersRepository, developerConverter);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idDeveloper = req.getParameter("idDeveloper");
        int id = Integer.parseInt(idDeveloper);
        String developerFirstName = req.getParameter("developerFirstName");
        String developerLastName = req.getParameter("developerLastName");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("phoneNumber");
        int salary = Integer.parseInt(req.getParameter("salary"));
        DevelopersDto developersDto = new DevelopersDto(developerFirstName,
                developerLastName, email, phoneNumber, salary);
        developersDto.setId(id);
        developerService.update(developersDto);
        req.setAttribute("message", "Developer updated");
        req.getRequestDispatcher("/WEB-INF/jsp/developer/updatedDeveloper.jsp").forward(req, resp);
    }
}
