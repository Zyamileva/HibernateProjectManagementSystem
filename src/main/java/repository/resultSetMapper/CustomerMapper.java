package repository.resultSetMapper;

import model.dao.CustomersDao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper implements Mapper<CustomersDao> {

    @Override
    public CustomersDao map(ResultSet resultSet) throws SQLException {
        CustomersDao customersDao = new CustomersDao();
        customersDao.setId(resultSet.getInt("id"));
        customersDao.setName(resultSet.getString("name"));
        customersDao.setContactPerson(resultSet.getString("contact_person"));
        customersDao.setPhoneNumber(resultSet.getString("phone"));
        return customersDao;
    }
}
