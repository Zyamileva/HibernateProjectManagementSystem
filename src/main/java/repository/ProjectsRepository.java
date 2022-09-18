package repository;

import model.dao.DevelopersDao;
import model.dao.ProjectsDao;
import repository.resultSetMapper.DevelopersMapper;
import repository.resultSetMapper.ProjectsMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectsRepository implements Repository<ProjectsDao> {
    private static final ProjectsMapper projectsMapper = new ProjectsMapper();
    private static final DevelopersMapper developersMapper = new DevelopersMapper();
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

    public List<DevelopersDao> ListDevelopersOfProjects(int id) {
        final String query = """
                select d.*
                from projects as pr
                join developers_projects as dp on pr.id=dp.project_id
                join developers as d on dp.developer_id=d.id
                where pr.id=?
                """;
        List<DevelopersDao> developers = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                developers.add(developersMapper.map(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return developers;
    }

    public int CountDevelopersOfProjects(int id) {
        final String query = """
                select count(d.id)
                from projects as pr
                join developers_projects as dp on pr.id=dp.project_id
                join developers as d on dp.developer_id=d.id
                where pr.id=?
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
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
        final String query = """
                select *
                from projects
                 """;
        List<ProjectsDao> projects = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                projects.add(projectsMapper.map(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return projects;
    }

    @Override
    public ProjectsDao update(ProjectsDao entity) {
        return null;
    }
}
