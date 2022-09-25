package repository;

import model.dao.DevelopersDao;
import repository.resultSetMapper.DevelopersMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DevelopersRepository implements Repository<DevelopersDao> {
    private final Connection connection;
    private DevelopersMapper developersMapper = new DevelopersMapper();

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
        final String query = """
                delete from developers
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
    public Optional<DevelopersDao> findById(int id) {
        final String FIND_BY_ID = "SELECT * FROM developers WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(developersMapper.map(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<DevelopersDao> findAll() {
        final String query = """
                select *
                from developers
                 """;
        List<DevelopersDao> developers = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                developers.add(developersMapper.map(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return developers;
    }

    @Override
    public void update(DevelopersDao entity) {
        final String query = """
                update developers set
                first_name = ?, 
                last_name = ?,
                email = ?,
                phone_number = ?,
                salary = ?
                where id = ?
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setString(3, entity.getEmail());
            preparedStatement.setString(4, entity.getPhoneNumber());
            preparedStatement.setInt(5, entity.getSalary());
            preparedStatement.setInt(6, entity.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<DevelopersDao> listOfSkillNameDevelopers(String skillName) {
        final String query = """
                select d.*
                from developers as d
                join developers_skills as ds ON ds.developer_id = d.id
                join skills as s on s.id = ds.skill_id and s.name = ?
                """;
        List<DevelopersDao> developers = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, skillName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                developers.add(developersMapper.map(resultSet));
            }
            return developers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<DevelopersDao> listOfSkillLevelDevelopers(String skillLevel) {
        final String query = """
                select d.*
                from developers as d
                join developers_skills as ds ON ds.developer_id = d.id
                join skills as s on s.id = ds.skill_id and s.level = ?
                """;
        List<DevelopersDao> developers = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, skillLevel);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                developers.add(developersMapper.map(resultSet));
            }
            return developers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveSkills(int idDeveloper, int idNameLevel) {
        final String INSERT = "INSERT INTO developers_skills(developer_id, skill_id)" +
                " VALUES(?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setInt(1, idDeveloper);
            preparedStatement.setInt(2, idNameLevel);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteOfIdsDeveloper(int developerId) {
        final String query = """
                delete from developers_skills
                where developer_id = ?
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, developerId);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOfIdsSkill(int skillId) {
        final String query = """
                delete from developers_skills
                where skill_id = ?
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, skillId);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
