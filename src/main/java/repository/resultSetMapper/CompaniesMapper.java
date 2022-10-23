package repository.resultSetMapper;

import model.dao.CompaniesDao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompaniesMapper implements Mapper<CompaniesDao> {

    @Override
    public CompaniesDao map(ResultSet resultSet) throws SQLException {
        CompaniesDao companiesDao = new CompaniesDao();
        companiesDao.setId(resultSet.getInt("id"));
        companiesDao.setName(resultSet.getString("name"));
        companiesDao.setStaff(resultSet.getInt("staff"));
        return companiesDao;
    }
}

