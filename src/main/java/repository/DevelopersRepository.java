package repository;

import model.dao.DevelopersDao;
import repository.resultSetMapper.DevelopersMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DevelopersRepository implements Repository<DevelopersDao> {
    private final Connection connection;
    private DevelopersMapper developersMapper=new DevelopersMapper();

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

    public List<DevelopersDao> listOfJavaDevelopers() {
        final String query = """
                select d.*
                from developers as d
                join developers_skills as ds ON ds.developer_id = d.id
                join skills as s on s.id = ds.skill_id and s.name ='Java'
                """;
        List<DevelopersDao> developers = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                developers.add(developersMapper.map(resultSet));
            }
            return developers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<DevelopersDao> listOfMiddleDevelopers() {
        final String query = """
                select d.*
                from developers as d
                join developers_skills as ds ON ds.developer_id = d.id
                join skills as s on s.id = ds.skill_id and s.level ='Middle'
                """;
        List<DevelopersDao> developers = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                developers.add(developersMapper.map(resultSet));
            }
            return developers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
