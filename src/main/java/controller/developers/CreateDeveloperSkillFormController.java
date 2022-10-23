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
import java.util.Set;

@WebServlet(urlPatterns = "/developers/create/skill/form")
public class CreateDeveloperSkillFormController extends HttpServlet {
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("developers"));
        int idSkill = Integer.parseInt(req.getParameter("skills"));

        try {
            developerService.saveSkills(id, idSkill);
        } catch (Exception ex) {
            req.setAttribute("message", "Error Add. This skill already exist.");
            req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
        }
        req.setAttribute("message", "Skill add");
        req.getRequestDispatcher("/WEB-INF/jsp/developer/createDeveloperSkillForm.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<DevelopersDto> allDevelopers = developerService.findAll();
        Set<SkillsDto> allSkills = skillsService.findAll();
        req.setAttribute("developers", allDevelopers);
        req.setAttribute("skills", allSkills);
        req.getRequestDispatcher("/WEB-INF/jsp/developer/createDevelopersSkillFormSearch.jsp").forward(req, resp);
    }
}
