package controller.skills;


import config.HibernateProvider;
import model.dto.SkillsDto;
import repository.SkillsRepository;
import service.SkillsService;
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

@WebServlet(urlPatterns = "/skills/allSkills")
public class FindAllSkillsController extends HttpServlet {
    private SkillsService skillsService;

    @Override
    public void init() throws ServletException {
        HibernateProvider dbProvider = new HibernateProvider();
        SkillsConverter skillsConverter = new SkillsConverter();
        SkillsRepository skillsRepository = new SkillsRepository(dbProvider);
        skillsService = new SkillsServiceImpl(skillsRepository, skillsConverter);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<SkillsDto> allSkills = skillsService.findAll();
        req.setAttribute("skills", allSkills);
        req.getRequestDispatcher("/WEB-INF/jsp/skill/findAllSkills.jsp").forward(req, resp);
    }
}
