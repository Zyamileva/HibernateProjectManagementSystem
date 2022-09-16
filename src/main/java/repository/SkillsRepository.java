package repository;

import model.dao.SkillsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SkillsRepository implements Repository<SkillsDao> {
    private final Connection connection;

    public SkillsRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public SkillsDao save(SkillsDao entity) {
        return null;
    }

    @Override
    public void delete(SkillsDao entity) {

    }

    @Override
    public Optional<SkillsDao> findById(int id) {
        return null;
    }

    @Override
    public List<SkillsDao> findAll() {
        return null;
    }

    @Override
    public SkillsDao update(SkillsDao entity) {
        return null;
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
