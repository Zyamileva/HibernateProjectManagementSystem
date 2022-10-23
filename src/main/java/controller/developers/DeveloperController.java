package controller.developers;

import config.DataBaseManagerConnector;
import model.dto.DevelopersDto;
import model.dto.SkillsDto;
import repository.DevelopersRepository;
import repository.SkillsRepository;
import service.DeveloperService;
import service.DeveloperServiceImpl;
import service.SkillsService;
import service.SkillsServiceImpl;
import service.converter.DeveloperConverter;
import service.converter.SkillsConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/developers")
public class DeveloperController extends HttpServlet {
    private DeveloperService developerService;
    private SkillsService skillsService;

    @Override
    public void init() throws ServletException {
        Connection connector = DataBaseManagerConnector.getInstance().getConnector();
        DeveloperConverter developerConverter = new DeveloperConverter();
        DevelopersRepository developersRepository = new DevelopersRepository(connector);
        developerService = new DeveloperServiceImpl(developersRepository, developerConverter);
        SkillsConverter skillsConverter = new SkillsConverter();
        SkillsRepository skillsRepository = new SkillsRepository(connector);
        skillsService = new SkillsServiceImpl(skillsRepository, skillsConverter);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String developerLastName = req.getParameter("developerLastName");
        if (developerService.findByName(developerLastName).isPresent()) {
            DevelopersDto developers = developerService.findByName(developerLastName).get();
            List<Integer> skillDeveloper = developerService.listSkillsOfDevelopers(developers.getId());
            List<SkillsDto> skills = skillsService.findAll().stream().filter(el -> skillDeveloper.contains(el.getId())).toList();
            req.setAttribute("developers", developers);
            req.setAttribute("skills", skills);
            req.getRequestDispatcher("/WEB-INF/jsp/developer/findDeveloper.jsp").forward(req, resp);
        } else {
            req.setAttribute("message", "Developer not found");
        }
        req.getRequestDispatcher("/WEB-INF/jsp/project/findProject.jsp").forward(req, resp);
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
