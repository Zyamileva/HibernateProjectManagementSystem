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

@WebServlet(urlPatterns = "/customers/update")
public class UpdateCustomerController extends HttpServlet {
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerName = req.getParameter("customerName");
        if (customersService.findByName(customerName).isPresent()) {
            String newName = req.getParameter("newCustomerName");
            String newContact = req.getParameter("newContact");
            String newPhone = req.getParameter("newPhone");
            CustomersDto customer = customersService.findByName(customerName).get();
            customer.setName(newName);
            customer.setContactPerson(newContact);
            customer.setPhoneNumber(newPhone);
            customersService.update(customer);
            req.setAttribute("message", "Customer: \"" + customer.getName() + "\" updated");
        } else {
            req.setAttribute("message", "Customer not found");
        }
        req.getRequestDispatcher("/WEB-INF/jsp/customer/updatedCustomer.jsp").forward(req, resp);
    }
}
