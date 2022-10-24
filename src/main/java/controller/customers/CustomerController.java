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

@WebServlet(urlPatterns = "/customers")
public class CustomerController extends HttpServlet {
    private CustomersService customersService;

    @Override
    public void init() throws ServletException {
        Connection connector = DataBaseManagerConnector.getInstance().getConnector();
        CustomersConverter customersConverter = new CustomersConverter();
        CustomersRepository customersRepository = new CustomersRepository(connector);
        customersService = new CustomersServiceImpl(customersRepository, customersConverter);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerName = req.getParameter("customerName");
        if (customersService.findByName(customerName).isPresent()) {
            CustomersDto customers = customersService.findByName(customerName).get();
            req.setAttribute("customers", customers);
            req.getRequestDispatcher("/WEB-INF/jsp/customer/findCustomer.jsp").forward(req, resp);
        } else {
            req.setAttribute("message", "Customer not found");
        }
        req.getRequestDispatcher("/WEB-INF/jsp/project/findProject.jsp").forward(req, resp);
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
