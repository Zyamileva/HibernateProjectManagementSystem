package repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    T save(T entity);

    void delete(T entity);

    Optional<T> findById(int id);

    List<T> findAll();

    T update(T entity);
}