package controller.skills;

import config.HibernateProvider;
import model.dto.SkillsDto;
import repository.SkillsRepository;
import service.SkillsServiceImpl;
import service.converter.SkillsConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Set;

@WebServlet(urlPatterns = "/skills/update")
public class UpdateSkillController extends HttpServlet {
    private SkillsServiceImpl skillsService;

    @Override
    public void init() throws ServletException {
        HibernateProvider dbProvider = new HibernateProvider();
        SkillsConverter skillsConverter = new SkillsConverter();
        SkillsRepository skillsRepository = new SkillsRepository(dbProvider);
        skillsService = new SkillsServiceImpl(skillsRepository, skillsConverter);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("skillName");
        if (!skillsService.findByNameSet(name).isEmpty()) {
            String newName = req.getParameter("newName");
            Set<SkillsDto> skills = skillsService.findByNameSet(name);
            for (SkillsDto skill : skills) {
                skill.setName(newName);
                skillsService.update(skill);
            }
            req.setAttribute("message", "Skill name updated");
        } else {
            req.setAttribute("message", "Skill" +
                    "" +
                    "" +
                    " name not found");
        }
        req.getRequestDispatcher("/WEB-INF/jsp/skill/updatedSkill.jsp").forward(req, resp);
    }
}
