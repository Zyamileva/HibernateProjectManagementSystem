package controller.companies;

import config.DataBaseManagerConnector;
import model.dto.CompaniesDto;
import repository.CompaniesRepository;
import service.CompaniesService;
import service.CompaniesServiceImpl;
import service.converter.CompaniesConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Set;

@WebServlet(urlPatterns = "/companies/allCompanies")
public class FindAllCompanyController extends HttpServlet {
    private CompaniesService companiesService;

    @Override
    public void init() throws ServletException {
        Connection connector = DataBaseManagerConnector.getInstance().getConnector();
        CompaniesConverter companiesConverter = new CompaniesConverter();
        CompaniesRepository companiesRepository = new CompaniesRepository(connector);
        companiesService = new CompaniesServiceImpl(companiesRepository, companiesConverter);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<CompaniesDto> allCompanies = companiesService.findAll();
        req.setAttribute("companies", allCompanies);
        req.getRequestDispatcher("/WEB-INF/jsp/company/findAllCompanies.jsp").forward(req, resp);
    }
}
