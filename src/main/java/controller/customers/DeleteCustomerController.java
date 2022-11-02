package controller.customers;

import config.HibernateProvider;
import model.dto.CustomersDto;
import model.dto.ProjectsDto;
import repository.CustomersRepository;
import repository.ProjectsRepository;
import service.*;
import service.converter.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

@WebServlet(urlPatterns = "/customers/delete")
public class DeleteCustomerController extends HttpServlet {
    private CustomersService customersService;
    private ProjectsService projectsService;

    @Override
    public void init() throws ServletException {
        HibernateProvider dbProvider = new HibernateProvider();
        SkillsConverter skillsConverter = new SkillsConverter();
        CompaniesConverter companiesConverter = new CompaniesConverter(skillsConverter);
        CustomersConverter customersConverter = new CustomersConverter(skillsConverter);
        DeveloperConverter developerConverter = new DeveloperConverter(skillsConverter);
        ProjectsConverter projctsConverter = new ProjectsConverter(companiesConverter, customersConverter);
        CustomersRepository customersRepository = new CustomersRepository(dbProvider);
        customersService = new CustomersServiceImpl(customersRepository, customersConverter);
        ProjectsRepository projectsRepository = new ProjectsRepository(dbProvider);
        projectsService = new ProjectsServiceImpl(projectsRepository, developerConverter, projctsConverter);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerName = req.getParameter("customerName");
        Optional<CustomersDto> byName = customersService.findByName(customerName);
        if (byName.isPresent()) {
            Set<ProjectsDto> projects = byName.get().getProjects();
            for (ProjectsDto element : projects) {
                projectsService.deleteOfIdsProject(element.getId());
                projectsService.delete(element);
            }
            customersService.delete(byName.get());
            req.setAttribute("message", "Customer: \"" + customerName + "\" deleted");
        } else {
            req.setAttribute("message", "Customer not found");
        }
        req.getRequestDispatcher("/WEB-INF/jsp/customer/deletedCustomer.jsp").forward(req, resp);
    }
}