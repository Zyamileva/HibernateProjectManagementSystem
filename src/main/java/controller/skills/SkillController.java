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

@WebServlet(urlPatterns = "/skills")
public class SkillController extends HttpServlet {
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
        String skillName = req.getParameter("skillName");
        if (skillsService.findByName(skillName).isPresent()) {
            SkillsDto skill = skillsService.findByName(skillName).get();
            req.setAttribute("skill", skill);
        } else {
            req.setAttribute("message", "Skill not found");
        }
        req.getRequestDispatcher("/WEB-INF/jsp/skill/findSkill.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String level = req.getParameter("level");
        SkillsDto skillsDto = new SkillsDto(name, level);
        skillsService.saveSkill(skillsDto);
        req.getRequestDispatcher("/WEB-INF/jsp/skill/savedSkill.jsp").forward(req, resp);
    }
}
