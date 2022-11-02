package controller.skills;

import config.HibernateProvider;
import model.dto.SkillsDto;
import repository.DevelopersRepository;
import repository.SkillsRepository;
import service.*;
import service.converter.DeveloperConverter;
import service.converter.SkillsConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet(urlPatterns = "/skills/delete")
public class DeleteSkillController extends HttpServlet {
    private SkillsService skillsService;
    private DeveloperService developerService;

    @Override
    public void init() throws ServletException {
        HibernateProvider dbProvider = new HibernateProvider();
        SkillsConverter skillsConverter = new SkillsConverter();
        SkillsRepository skillsRepository = new SkillsRepository(dbProvider);
        skillsService = new SkillsServiceImpl(skillsRepository, skillsConverter);
        DeveloperConverter developerConverter = new DeveloperConverter(skillsConverter);
        DevelopersRepository developersRepository = new DevelopersRepository(dbProvider);
        developerService = new DeveloperServiceImpl(developersRepository, developerConverter);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String skillName = req.getParameter("skillName");
        if (skillsService.findByName(skillName).isPresent()) {
            Set<SkillsDto> byNameSet = skillsService.findByNameSet(skillName);
            for (SkillsDto skill : byNameSet) {
                developerService.deleteDevelopersOfIdsSkill(skill.getId());
                skillsService.delete(skill);
            }
            req.setAttribute("message", "Skill: \"" + skillName + "\" deleted");
        } else {
            req.setAttribute("message", "Skill not found");
        }
        req.getRequestDispatcher("/WEB-INF/jsp/skill/deletedSkill.jsp").forward(req, resp);
    }
}
