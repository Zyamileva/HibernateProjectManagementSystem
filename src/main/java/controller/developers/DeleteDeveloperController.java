package controller.developers;

import config.HibernateProvider;
import model.dto.DevelopersDto;
import repository.DevelopersRepository;
import repository.ProjectsRepository;
import service.DeveloperService;
import service.DeveloperServiceImpl;
import service.ProjectsService;
import service.ProjectsServiceImpl;
import service.converter.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/developers/delete")
public class DeleteDeveloperController extends HttpServlet {
    private DeveloperService developerService;
    private ProjectsService projectsService;

    @Override
    public void init() throws ServletException {
        HibernateProvider dbProvider = new HibernateProvider();
        SkillsConverter skillsConverter = new SkillsConverter();
        CompaniesConverter companiesConverter = new CompaniesConverter(skillsConverter);
        CustomersConverter customersConverter = new CustomersConverter(skillsConverter);
        DeveloperConverter developerConverter = new DeveloperConverter(skillsConverter, companiesConverter, customersConverter);
        DevelopersRepository developersRepository = new DevelopersRepository(dbProvider);
        developerService = new DeveloperServiceImpl(developersRepository, developerConverter);
        ProjectsConverter projectsConverter = new ProjectsConverter(companiesConverter, customersConverter, developerConverter);
        ProjectsRepository projectsRepository = new ProjectsRepository(dbProvider);
        projectsService = new ProjectsServiceImpl(projectsRepository, developerConverter, projectsConverter);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("developers"));
        projectsService.deleteProjectOfIdsDeveloper(id);
        developerService.deleteSkillsOfDeveloper(id);
        DevelopersDto developersDto = developerService.findById(id).get();
        developerService.delete(developersDto);
        req.setAttribute("message", "Developer: \"" + developersDto.getLastName() + " "
                + developersDto.getLastName() + "\" deleted");
        req.getRequestDispatcher("/WEB-INF/jsp/developer/deletedDeveloper.jsp").forward(req, resp);
    }
}