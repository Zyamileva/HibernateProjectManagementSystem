package repository;

import model.dao.CompaniesDao;
import repository.resultSetMapper.CompaniesMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompaniesRepository implements Repository<CompaniesDao> {
    private final Connection connection;
    private CompaniesMapper companiesMapper = new CompaniesMapper();

    public CompaniesRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public CompaniesDao save(CompaniesDao entity) {
        final String INSERT = "INSERT INTO companies(name, staff) VALUES(?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getStaff());
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
    public void delete(CompaniesDao entity) {
        final String query = """
                delete from companies
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
    public Optional<CompaniesDao> findById(int id) {
        final String FIND_BY_ID = "SELECT * FROM companies WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(companiesMapper.map(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<CompaniesDao> findAll() {
        final String query = """
                select *
                from companies
                 """;
        List<CompaniesDao> companies = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                companies.add(companiesMapper.map(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return companies;
    }

    @Override
    public CompaniesDao update(CompaniesDao entity) {
        final String query = """
                update companies set
                name = ?, 
                staff = ?              
                where id = ?
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getStaff());
            preparedStatement.setInt(3, entity.getId());
            preparedStatement.execute();
            connection.commit();
            return entity;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
