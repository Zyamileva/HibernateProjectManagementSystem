package repository;

import config.DataBaseManagerConnector;
import model.dao.CompaniesDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class CompaniesRepository implements Repository<CompaniesDao> {
    private final DataBaseManagerConnector manager;

    private static final String INSERT = "INSERT INTO companies(name, staff) VALUES(?,?)";

    public CompaniesRepository(DataBaseManagerConnector manager) {
        this.manager = manager;
    }

    @Override
    public CompaniesDao save(CompaniesDao entity) {
        try (Connection connection = manager.getConnector();) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getStaff());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(CompaniesDao entity) {
    }

    @Override
    public Optional<CompaniesDao> findById(int id) {
        return null;
    }

    @Override
    public List<CompaniesDao> findAll() {
        return null;
    }

    @Override
    public CompaniesDao update(CompaniesDao entity) {
        return null;
    }
}
