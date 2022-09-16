package repository.resultSetMapper;

import model.PersistentEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper <T extends PersistentEntity> {
    T map(ResultSet resultSet) throws SQLException;
}