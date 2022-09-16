package repository;

import model.dao.CustomersDao;

import java.util.List;
import java.util.Optional;

public class CustomersRepository implements Repository<CustomersDao>{
    @Override
    public CustomersDao save(CustomersDao entity) {
        return null;
    }

    @Override
    public void delete(CustomersDao entity) {

    }

    @Override
    public Optional<CustomersDao> findById(int id) {
        return null;
    }

    @Override
    public List<CustomersDao> findAll() {
        return null;
    }

    @Override
    public CustomersDao update(CustomersDao entity) {
        return null;
    }
}
