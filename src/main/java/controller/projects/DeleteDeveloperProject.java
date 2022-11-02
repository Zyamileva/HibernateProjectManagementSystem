package controller.projects;

import config.HibernateProvider;
import model.dto.DevelopersDto;
import model.dto.ProjectsDto;
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
import java.util.Arrays;
import java.util.List;

@WebServlet(urlPatterns = "/projects/delete/developer")
public class DeleteDeveloperProject extends HttpServlet {
    private DeveloperService developerService;
    private ProjectsService projectsService;

    @Override
    public void init() throws ServletException {
        HibernateProvider dbProvider = new HibernateProvider();
        SkillsConverter skillsConverter = new SkillsConverter();
        CompaniesConverter companiesConverter = new CompaniesConverter(skillsConverter);
        CustomersConverter customersConverter = new CustomersConverter(skillsConverter);
        DeveloperConverter developerConverter = new DeveloperConverter(skillsConverter);
        DevelopersRepository developersRepository = new DevelopersRepository(dbProvider);
        developerService = new DeveloperServiceImpl(developersRepository, developerConverter);
        ProjectsConverter projectsConverter = new ProjectsConverter(companiesConverter, customersConverter);
        ProjectsRepository projectsRepository = new ProjectsRepository(dbProvider);
        projectsService = new ProjectsServiceImpl(projectsRepository, developerConverter, projectsConverter);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameProject = req.getParameter("project");
        String[] idsDeveloper = req.getParameterValues("developerId");
        int idProject = projectsService.findByName(nameProject).get().getId();

        List<Integer> ids = Arrays.stream(idsDeveloper)
                .map(Integer::parseInt).toList();
        try {
            for (int id : ids) {
                projectsService.deleteOfIdsDeveloperOfProject(id, idProject);
            }
        } catch (Exception ex) {
            req.setAttribute("message", "Error delete. Developer does not exist.");
            req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
        }
        req.setAttribute("message", "Developers delete");
        req.getRequestDispatcher("/WEB-INF/jsp/project/deleteProjectDeveloperForm.jsp").forward(req, resp);
    }

}
