package repository;

import model.dao.ProjectsDao;
import repository.resultSetMapper.ProjectsMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProjectsRepository implements Repository<ProjectsDao> {
    private static final ProjectsMapper projectsMapper = new ProjectsMapper();
    Connection connection;

    public ProjectsRepository(Connection connection) {
        this.connection = connection;
    }

    public int sallaryOfProjects(int id) {
        final String query = """
                select SUM(salary) as all_sallary, pr.name as name
                from projects as pr
                join developers_projects as dp on pr.id=dp.project_id
                join developers as d on dp.developer_id=d.id
                where pr.id=?
                group by name
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public ProjectsDao save(ProjectsDao entity) {
        return null;
    }

    @Override
    public void delete(ProjectsDao entity) {

    }

    @Override
    public Optional<ProjectsDao> findById(int id) {
        final String FIND_BY_ID = "SELECT * FROM projects WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(projectsMapper.map(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<ProjectsDao> findAll() {
        return null;
    }

    @Override
    public ProjectsDao update(ProjectsDao entity) {
        return null;
    }
}
