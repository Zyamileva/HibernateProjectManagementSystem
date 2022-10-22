package controller.customers;

import config.DataBaseManagerConnector;
import model.dto.CustomersDto;
import repository.CustomersRepository;
import service.CustomersService;
import service.CustomersServiceImpl;
import service.converter.CustomersConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Set;

@WebServlet(urlPatterns = "/customers/update")
public class UpdateCustomerController extends HttpServlet {
    private CustomersService customersService;

    @Override
    public void init() throws ServletException {
        Connection connector = DataBaseManagerConnector.getInstance().getConnector();
        CustomersConverter customersConverter = new CustomersConverter();
        CustomersRepository customersRepository = new CustomersRepository(connector);
        customersService = new CustomersServiceImpl(customersRepository, customersConverter);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerName = req.getParameter("customerName");
        if (!customersService.findByName(customerName).isEmpty()) {
            String newName = req.getParameter("newCustomerName");
            String newContact = req.getParameter("newContact");
            String newPhone = req.getParameter("newPhone");
            Set<CustomersDto> byName = customersService.findByName(customerName);
            for (CustomersDto customer : byName) {
                customer.setName(newName);
                customer.setContactPerson(newContact);
                customer.setPhoneNumber(newPhone);
                customersService.update(customer);
                req.setAttribute("message", "Customer: \"" + customer.getName() + "\" updated");
            }
        } else {
            req.setAttribute("message", "Customer not found");
        }
        req.getRequestDispatcher("/WEB-INF/jsp/customer/updatedCustomer.jsp").forward(req, resp);
    }
}
