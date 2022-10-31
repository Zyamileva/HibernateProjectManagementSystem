package controller.customers;

import config.HibernateProvider;
import model.dto.CustomersDto;
import repository.CustomersRepository;
import service.CustomersService;
import service.CustomersServiceImpl;
import service.converter.CustomersConverter;
import service.converter.SkillsConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet(urlPatterns = "/customers/allCustomers")
public class FindAllCustomerController extends HttpServlet {
    private CustomersService customersService;

    @Override
    public void init() throws ServletException {
        HibernateProvider dbProvider = new HibernateProvider();
        SkillsConverter skillsConverter = new SkillsConverter();
        CustomersConverter customersConverter = new CustomersConverter(skillsConverter);
        CustomersRepository customersRepository = new CustomersRepository(dbProvider);
        customersService = new CustomersServiceImpl(customersRepository, customersConverter);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<CustomersDto> allCustomers = customersService.findAll();
        req.setAttribute("customers", allCustomers);
        req.getRequestDispatcher("/WEB-INF/jsp/customer/findAllCustomers.jsp").forward(req, resp);
    }
}
