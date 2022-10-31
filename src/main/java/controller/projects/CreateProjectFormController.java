package controller.projects;

import config.HibernateProvider;
import model.dto.CompaniesDto;
import model.dto.CustomersDto;
import repository.CompaniesRepository;
import repository.CustomersRepository;
import service.CompaniesService;
import service.CompaniesServiceImpl;
import service.CustomersService;
import service.CustomersServiceImpl;
import service.converter.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet(urlPatterns = "/projects/create/form")
public class CreateProjectFormController extends HttpServlet {
    private CompaniesService companiesService;
    private CustomersService customersService;

    @Override
    public void init() throws ServletException {
        HibernateProvider dbProvider = new HibernateProvider();
        SkillsConverter skillsConverter = new SkillsConverter();
        CompaniesConverter companiesConverter = new CompaniesConverter(skillsConverter);
        CustomersConverter customersConverter = new CustomersConverter(skillsConverter);
        CompaniesRepository companiesRepository = new CompaniesRepository(dbProvider);
        companiesService = new CompaniesServiceImpl(companiesRepository, companiesConverter);
        CustomersRepository customersRepository = new CustomersRepository(dbProvider);
        customersService = new CustomersServiceImpl(customersRepository, customersConverter);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<CustomersDto> customer = customersService.findAll();
        Set<CompaniesDto> company = companiesService.findAll();
        req.setAttribute("company", company);
        req.setAttribute("customer", customer);
        req.getRequestDispatcher("/WEB-INF/jsp/project/createProjectForm.jsp").forward(req, resp);
    }
}
