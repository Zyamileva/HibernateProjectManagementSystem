package repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface Repository<T> {
    T save(T entity);

    void delete(T entity);

    Optional<T> findByName(String name);

    Optional<T> findById(int id);

    Set<T> findAll();

    void update(T entity);
}