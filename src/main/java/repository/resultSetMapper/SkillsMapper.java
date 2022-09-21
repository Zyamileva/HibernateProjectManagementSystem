package repository.resultSetMapper;

import model.dao.SkillsDao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SkillsMapper implements Mapper<SkillsDao> {
    @Override
    public SkillsDao map(ResultSet resultSet) throws SQLException {
        SkillsDao skillsDao = new SkillsDao();
        skillsDao.setId(resultSet.getInt("id"));
        skillsDao.setName(resultSet.getString("name"));
        skillsDao.setLevel(resultSet.getString("level"));
        return skillsDao;
    }
}
