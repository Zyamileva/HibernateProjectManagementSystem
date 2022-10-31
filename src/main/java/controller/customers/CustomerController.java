package controller.customers;

import config.HibernateProvider;
import model.dto.CustomersDto;
import repository.CustomersRepository;
import service.CustomersService;
import service.CustomersServiceImpl;
import service.converter.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/customers")
public class CustomerController extends HttpServlet {
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
        String customerName = req.getParameter("customerName");
        if (customersService.findByName(customerName).isPresent()) {
            CustomersDto customers = customersService.findByName(customerName).get();
            req.setAttribute("customers", customers);
        } else {
            req.setAttribute("message", "Customer not found");
        }
        req.getRequestDispatcher("/WEB-INF/jsp/customer/findCustomer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerName = req.getParameter("customerName");
        String contactPerson = req.getParameter("contactPerson");
        String phone = req.getParameter("phone");
        CustomersDto customersDto = new CustomersDto(customerName, contactPerson, phone);
        customersService.saveCustomer(customersDto);
        req.getRequestDispatcher("/WEB-INF/jsp/customer/savedCustomer.jsp").forward(req, resp);
    }
}
