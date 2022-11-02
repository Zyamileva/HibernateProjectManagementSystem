package controller.companies;

import config.HibernateProvider;
import model.dto.CompaniesDto;
import repository.CompaniesRepository;
import service.CompaniesService;
import service.CompaniesServiceImpl;
import service.converter.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/companies")
public class CompanyController extends HttpServlet {
    private CompaniesService companiesService;

    @Override
    public void init() throws ServletException {
        HibernateProvider dbProvider = new HibernateProvider();
        SkillsConverter skillConverter = new SkillsConverter();
        CompaniesConverter companiesConverter = new CompaniesConverter(skillConverter);
        CompaniesRepository companiesRepository = new CompaniesRepository(dbProvider);
        companiesService = new CompaniesServiceImpl(companiesRepository, companiesConverter);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String companyName = req.getParameter("companyName");
        if (companiesService.findByName(companyName).isPresent()) {
            CompaniesDto companies = companiesService.findByName(companyName).get();
            req.setAttribute("companies", companies);
        } else {
            req.setAttribute("message", "Company not found");
        }
        req.getRequestDispatcher("/WEB-INF/jsp/company/findCompany.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String companyName = req.getParameter("companyName");
        if (companiesService.findByName(companyName).isPresent()) {
            req.setAttribute("message", "The company already exists");
            req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
        } else {
            int staff = Integer.parseInt(req.getParameter("staff"));
            CompaniesDto companiesDto = new CompaniesDto(companyName, staff);
            companiesService.saveCompanies(companiesDto);
            req.getRequestDispatcher("/WEB-INF/jsp/company/savedCompany.jsp").forward(req, resp);
        }
    }
}
