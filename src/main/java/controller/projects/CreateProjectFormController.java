package controller.projects;

import config.DataBaseManagerConnector;
import model.dto.CompaniesDto;
import model.dto.CustomersDto;
import repository.CompaniesRepository;
import repository.CustomersRepository;
import service.CompaniesService;
import service.CompaniesServiceImpl;
import service.CustomersService;
import service.CustomersServiceImpl;
import service.converter.CompaniesConverter;
import service.converter.CustomersConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Set;

@WebServlet(urlPatterns = "/projects/create/form")
public class CreateProjectFormController extends HttpServlet {
    private CompaniesService companiesService;
    private CustomersService customersService;

    @Override
    public void init() throws ServletException {
        Connection connector = DataBaseManagerConnector.getInstance().getConnector();
        CompaniesConverter companiesConverter = new CompaniesConverter();
        CompaniesRepository companiesRepository = new CompaniesRepository(connector);
        companiesService = new CompaniesServiceImpl(companiesRepository, companiesConverter);
        CustomersConverter customersConverter = new CustomersConverter();
        CustomersRepository customersRepository = new CustomersRepository(connector);
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
