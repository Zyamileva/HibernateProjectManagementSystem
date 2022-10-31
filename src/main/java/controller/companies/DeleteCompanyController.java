package controller.companies;

import config.HibernateProvider;
import model.dto.CompaniesDto;
import model.dto.ProjectsDto;
import repository.CompaniesRepository;
import repository.ProjectsRepository;
import service.CompaniesService;
import service.CompaniesServiceImpl;
import service.ProjectsService;
import service.ProjectsServiceImpl;
import service.converter.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet(urlPatterns = "/companies/delete")
public class DeleteCompanyController extends HttpServlet {
    private CompaniesService companiesService;
    private ProjectsService projectsService;

    @Override
    public void init() throws ServletException {
        HibernateProvider dbProvider = new HibernateProvider();
        SkillsConverter skillsConverter=new SkillsConverter();
        CompaniesConverter companiesConverter = new CompaniesConverter(skillsConverter);
        CustomersConverter customersConverter = new CustomersConverter(skillsConverter);
        DeveloperConverter developerConverter = new DeveloperConverter(skillsConverter,companiesConverter,customersConverter);
        ProjectsConverter projectsConverter = new ProjectsConverter(companiesConverter,customersConverter,developerConverter);
        CompaniesRepository companiesRepository = new CompaniesRepository(dbProvider);
        companiesService = new CompaniesServiceImpl(companiesRepository, companiesConverter);
        ProjectsRepository projectsRepository = new ProjectsRepository(dbProvider);
        projectsService = new ProjectsServiceImpl(projectsRepository, developerConverter, projectsConverter);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String companyName = req.getParameter("companyName");
        if (companiesService.findByName(companyName).isPresent()) {
//            Set<ProjectsDto> projectsDtos = projectsService.findAll().stream()
//                    .filter(el -> companiesService.findById(el.getCompanies().getId()).get().getName().equals(companyName)).collect(Collectors.toSet());
//            for (ProjectsDto element : projectsDtos) {
//                projectsService.deleteOfIdsProject(element.getId());
//                projectsService.delete(element);
//            }
            CompaniesDto company = companiesService.findByName(companyName).get();

            for(ProjectsDto element: company.getProjects()) {
                projectsService.delete(element);
            }
            companiesService.delete(company);
            req.setAttribute("message", "Company: \"" + company.getName() + "\" deleted");
        } else {
            req.setAttribute("message", "Company not found");
        }
        req.getRequestDispatcher("/WEB-INF/jsp/company/deletedCompany.jsp").forward(req, resp);
    }
}
