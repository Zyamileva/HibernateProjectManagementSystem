package repository;

import model.dao.DevelopersDao;
import model.dao.SkillsDao;
import repository.resultSetMapper.SkillsMapper;

import java.sql.*;
import java.util.*;

public class SkillsRepository implements Repository<SkillsDao> {
    private final Connection connection;
    private static final SkillsMapper skillsMapper = new SkillsMapper();

    public SkillsRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public SkillsDao save(SkillsDao entity) {
        final String INSERT = "INSERT INTO skills(name, level)" +
                " VALUES(?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getLevel());
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
    public void delete(SkillsDao entity) {
        final String query = """
                delete from skills
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
    public Set<SkillsDao> findByName(String name) {
        final String FIND_BY_NAME = "SELECT * FROM skills WHERE name = ?";
        Set<SkillsDao> skills = new HashSet<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                skills.add(skillsMapper.map(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return skills;
    }

    @Override
    public Optional<SkillsDao> findById(int id) {
        final String FIND_BY_ID = "SELECT * FROM skills WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(skillsMapper.map(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Set<SkillsDao> findAll() {
        final String query = """
                select *
                from skills
                 """;
        Set<SkillsDao> skills = new HashSet<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                skills.add(skillsMapper.map(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return skills;
    }

    @Override
    public void update(SkillsDao entity) {
        final String query = """
                update skills set
                   name = ?,
                   level = ?
                   where id = ?
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getLevel());
            preparedStatement.setInt(3, entity.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public int findByNameLevel(String name, String level) {
        final String EXIST = "SELECT id FROM skills WHERE name = ? AND level = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(EXIST);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, level);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
