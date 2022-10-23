package repository;

import model.dao.CustomersDao;
import repository.resultSetMapper.CustomerMapper;

import java.sql.*;
import java.util.*;

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
        return entity;
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<CustomersDao> findByName(String name) {
        final String FIND_BY_NAME = "SELECT * FROM customers WHERE name LIKE ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME);
            preparedStatement.setString(1, name);
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
    public Set<CustomersDao> findAll() {
        final String query = """
                select *
                from customers
                 """;
        Set<CustomersDao> customers = new HashSet<>();
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
    public void update(CustomersDao entity) {
        final String query = """
                update customers set
                name = ?, 
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
