package controller.companies;

import config.DataBaseManagerConnector;
import model.dto.CompaniesDto;
import model.dto.ProjectsDto;
import repository.CompaniesRepository;
import repository.ProjectsRepository;
import service.CompaniesService;
import service.CompaniesServiceImpl;
import service.ProjectsService;
import service.ProjectsServiceImpl;
import service.converter.CompaniesConverter;
import service.converter.DeveloperConverter;
import service.converter.ProjectsConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/companies/delete")
public class DeleteCompanyController extends HttpServlet {
    private CompaniesService companiesService;
    private ProjectsService projectsService;

    @Override
    public void init() throws ServletException {
        Connection connector = DataBaseManagerConnector.getInstance().getConnector();
        CompaniesConverter companiesConverter = new CompaniesConverter();
        CompaniesRepository companiesRepository = new CompaniesRepository(connector);
        companiesService = new CompaniesServiceImpl(companiesRepository, companiesConverter);
        ProjectsConverter projectsConverter = new ProjectsConverter();
        DeveloperConverter developerConverter = new DeveloperConverter();
        ProjectsRepository projectsRepository = new ProjectsRepository(connector);
        projectsService = new ProjectsServiceImpl(projectsRepository, developerConverter, projectsConverter);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String companyName = req.getParameter("companyName");
        if (!companiesService.findByName(companyName).isEmpty()) {
            Set<ProjectsDto> projectsDtos = projectsService.findAll().stream()
                    .filter(el -> companiesService.findById(el.getCompanyId()).get().getName().equals(companyName)).collect(Collectors.toSet());
            for (ProjectsDto element : projectsDtos) {
                projectsService.deleteOfIdsProject(element.getId());
                projectsService.delete(element);
            }
            CompaniesDto company = companiesService.findByName(companyName).get();
            companiesService.delete(company);
            req.setAttribute("message", "Company: \"" + company.getName() + "\" deleted");
        } else {
            req.setAttribute("message", "Company not found");
        }
        req.getRequestDispatcher("/WEB-INF/jsp/company/deletedCompany.jsp").forward(req, resp);
    }
}
