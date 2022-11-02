package controller.developers;

import config.HibernateProvider;
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
import java.util.List;
import java.util.Optional;
import java.util.Set;

@WebServlet(urlPatterns = "/developers")
public class DeveloperController extends HttpServlet {
    private DeveloperServiceImpl developerService;
    private SkillsService skillsService;

    @Override
    public void init() throws ServletException {
        HibernateProvider dbProvider = new HibernateProvider();
        SkillsConverter skillsConverter = new SkillsConverter();
        DeveloperConverter developerConverter = new DeveloperConverter(skillsConverter);
        DevelopersRepository developersRepository = new DevelopersRepository(dbProvider);
        developerService = new DeveloperServiceImpl(developersRepository, developerConverter);
        SkillsRepository skillsRepository = new SkillsRepository(dbProvider);
        skillsService = new SkillsServiceImpl(skillsRepository, skillsConverter);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String developerLastName = req.getParameter("developerLastName");
        Set<DevelopersDto> developers = developerService.findByNameSet(developerLastName);
        if (!developers.isEmpty()) {
           // DevelopersDto developers = developerService.findByName(developerLastName).get();

                req.setAttribute("developers", developers);



//            List<Integer> skillDeveloper = developerService.listSkillsOfDevelopers(developers.getId());
//            List<SkillsDto> skills = skillsService.findAll().stream().filter(el -> skillDeveloper.contains(el.getId())).toList();
            //req.setAttribute("developers", developers);
          //  req.setAttribute("skills", skills);
         //   req.getRequestDispatcher("/WEB-INF/jsp/developer/findDeveloper.jsp").forward(req, resp);
        } else {
            req.setAttribute("message", "Developer not found");
        }
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
        try {
            developerService.saveDeveloper(developersDto);
            req.getRequestDispatcher("/WEB-INF/jsp/developer/savedDeveloper.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("message", "Such a phone/email is already in the database");
            req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
        }
    }
}
