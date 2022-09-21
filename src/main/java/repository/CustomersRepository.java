package repository;

import model.dao.CustomersDao;
import repository.resultSetMapper.CustomerMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomersRepository implements Repository<CustomersDao> {
    private final Connection connection;
    private CustomerMapper customerMapper = new CustomerMapper();

    public CustomersRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public CustomersDao save(CustomersDao entity) {
        final String INSERT = "INSERT INTO customers (name, contact_person, phone) VALUES(?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getContactPerson());
            preparedStatement.setString(3, entity.getPhoneNumber());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(CustomersDao entity) {
        final String query = """
                delete from customers
                where id = ?
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<CustomersDao> findById(int id) {
        final String FIND_BY_ID = "SELECT * FROM customers WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(customerMapper.map(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<CustomersDao> findAll() {
        final String query = """
                select *
                from customers
                 """;
        List<CustomersDao> customers = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                customers.add(customerMapper.map(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    @Override
    public CustomersDao update(CustomersDao entity) {
        final String query = """
                update customers set
                first_name = ?, 
                contact_person = ?,
                phone = ?
                where id = ?
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getContactPerson());
            preparedStatement.setString(3, entity.getPhoneNumber());
            preparedStatement.setInt(4, entity.getId());
            preparedStatement.execute();
            connection.commit();
            return entity;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
