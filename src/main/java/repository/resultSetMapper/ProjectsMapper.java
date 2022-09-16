package repository.resultSetMapper;

import model.dao.ProjectsDao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectsMapper implements Mapper<ProjectsDao> {
    @Override
    public ProjectsDao map(ResultSet resultSet) throws SQLException {
        ProjectsDao projectsDao = new ProjectsDao();
        projectsDao.setId(resultSet.getInt("id"));
        projectsDao.setName(resultSet.getString("name"));
        projectsDao.setTask_difficulty(resultSet.getString("task_difficulty"));
        projectsDao.setCompanyId(resultSet.getInt("company_id"));
        projectsDao.setCustomerId(resultSet.getInt("customer_id"));
        projectsDao.setCost(resultSet.getInt("cost"));
        return projectsDao;
    }
}
