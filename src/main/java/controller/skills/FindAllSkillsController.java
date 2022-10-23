package controller.skills;

import config.DataBaseManagerConnector;
import model.dto.CompaniesDto;
import model.dto.SkillsDto;
import repository.CompaniesRepository;
import repository.SkillsRepository;
import service.CompaniesService;
import service.CompaniesServiceImpl;
import service.SkillsService;
import service.SkillsServiceImpl;
import service.converter.CompaniesConverter;
import service.converter.SkillsConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Set;

@WebServlet(urlPatterns = "/skills/allSkills")
public class FindAllSkillsController extends HttpServlet {
    private SkillsService skillsService;

    @Override
    public void init() throws ServletException {
        Connection connector = DataBaseManagerConnector.getInstance().getConnector();
        SkillsConverter skillsConverter = new SkillsConverter();
        SkillsRepository skillsRepository = new SkillsRepository(connector);
        skillsService = new SkillsServiceImpl(skillsRepository, skillsConverter);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<SkillsDto> allSkills = skillsService.findAll();
        req.setAttribute("skills", allSkills);
        req.getRequestDispatcher("/WEB-INF/jsp/skill/findAllSkills.jsp").forward(req, resp);
    }
}
