package repository.resultSetMapper;

import model.dao.DevelopersDao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DevelopersMapper implements Mapper<DevelopersDao> {
    public DevelopersDao map(ResultSet resultSet) throws SQLException {
        DevelopersDao developersDao = new DevelopersDao();
        developersDao.setId(resultSet.getInt("id"));
        developersDao.setFirstName(resultSet.getString("first_name"));
        developersDao.setLastName(resultSet.getString("last_name"));
        developersDao.setEmail(resultSet.getString("email"));
        developersDao.setPhoneNumber(resultSet.getString("phone_number"));
        developersDao.setSalary(resultSet.getInt("salary"));
        return developersDao;
    }
}
