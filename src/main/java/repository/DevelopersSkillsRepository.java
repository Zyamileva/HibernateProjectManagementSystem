package repository;

import model.dao.DevelopersSkillsDao;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class DevelopersSkillsRepository implements Repository<DevelopersSkillsDao> {
    private final Connection connection;

    public DevelopersSkillsRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public DevelopersSkillsDao save(DevelopersSkillsDao entity) {
        final String INSERT = "INSERT INTO developers_skills(developer_id, skill_id)" +
                " VALUES(?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setInt(1, entity.getDeveloperId());
            preparedStatement.setInt(2, entity.getSkillId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public void delete(DevelopersSkillsDao entity) {

    }

    @Override
    public Optional<DevelopersSkillsDao> findById(int id) {
        return null;
    }

    @Override
    public List<DevelopersSkillsDao> findAll() {
        return null;
    }

    @Override
    public DevelopersSkillsDao update(DevelopersSkillsDao entity) {
        return null;
    }

    public boolean existByIds(int developerId, int skillId) {
        final String EXIST = "SELECT * FROM developers_skills WHERE developer_id = ? AND skill_id = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(EXIST);
            preparedStatement.setInt(1, developerId);
            preparedStatement.setInt(2, skillId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
