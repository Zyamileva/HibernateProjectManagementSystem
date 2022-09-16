package repository;

import config.DataBaseManagerConnector;
import model.dao.DevelopersDao;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class DevelopersRepository implements Repository<DevelopersDao> {
    private final Connection connection;

    public DevelopersRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public DevelopersDao save(DevelopersDao entity) {
        final String INSERT = "INSERT INTO developers(first_name, last_name, email, phone_number, salary)" +
                " VALUES(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setString(3, entity.getEmail());
            preparedStatement.setString(4, entity.getPhoneNumber());
            preparedStatement.setInt(5, entity.getSalary());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public void delete(DevelopersDao entity) {
    }

    @Override
    public Optional<DevelopersDao> findById(int id) {
        return null;
    }

    @Override
    public List<DevelopersDao> findAll() {
        return null;
    }

    @Override
    public DevelopersDao update(DevelopersDao entity) {
        return null;
    }

}
