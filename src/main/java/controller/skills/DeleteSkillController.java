package controller.skills;

import config.DataBaseManagerConnector;
import model.dto.CompaniesDto;
import model.dto.ProjectsDto;
import model.dto.SkillsDto;
import repository.CompaniesRepository;
import repository.DevelopersRepository;
import repository.ProjectsRepository;
import repository.SkillsRepository;
import service.*;
import service.converter.CompaniesConverter;
import service.converter.DeveloperConverter;
import service.converter.ProjectsConverter;
import service.converter.SkillsConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/skills/delete")
public class DeleteSkillController extends HttpServlet {
    private SkillsService skillsService;
    private DeveloperService developerService;

    @Override
    public void init() throws ServletException {
        Connection connector = DataBaseManagerConnector.getInstance().getConnector();
        SkillsConverter skillsConverter = new SkillsConverter();
        SkillsRepository skillsRepository = new SkillsRepository(connector);
        skillsService = new SkillsServiceImpl(skillsRepository, skillsConverter);
        DeveloperConverter developerConverter = new DeveloperConverter();
        DevelopersRepository developersRepository = new DevelopersRepository(connector);
        developerService = new DeveloperServiceImpl(developersRepository, developerConverter);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String skillName = req.getParameter("skillName");
        if (skillsService.findByName(skillName).isPresent()) {
            Set<SkillsDto> byNameSet = skillsService.findByNameSet(skillName);
            for (SkillsDto skill : byNameSet) {
            developerService.deleteDevelopersOfIdsSkill(skill.getId());
            skillsService.delete(skill);}
            req.setAttribute("message", "Skill: \"" + skillName + "\" deleted");
        } else {
            req.setAttribute("message", "Skill not found");
        }
        req.getRequestDispatcher("/WEB-INF/jsp/skill/deletedSkill.jsp").forward(req, resp);
    }
}
