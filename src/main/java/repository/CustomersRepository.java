package repository;

import config.HibernateProvider;
import model.dao.CustomersDao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.*;
import java.util.stream.Collectors;

public class CustomersRepository implements Repository<CustomersDao> {

    private final HibernateProvider manager;

    public CustomersRepository(HibernateProvider manager) {
        this.manager = manager;
    }

    @Override
    public CustomersDao save(CustomersDao entity) {
        try (Session session = manager.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public void delete(CustomersDao entity) {
        try (Session session = manager.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<CustomersDao> findByName(String name) {
        try (Session session = manager.openSession()) {
            return Optional.ofNullable(session.createQuery("FROM CustomersDao as customers WHERE customers.name = :name",
                            CustomersDao.class)
                    .setParameter("name", name).getSingleResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<CustomersDao> findById(int id) {
        try (Session session = manager.openSession()) {
            return Optional.ofNullable(session.createQuery("FROM CustomersDao as customers WHERE customers.id = :id",
                            CustomersDao.class)
                    .setParameter("id", id).getSingleResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    @Override
    public Set<CustomersDao> findAll() {
        try (final Session session = manager.openSession()) {
            return session.createQuery("SELECT customers FROM CustomersDao as customers", CustomersDao.class)
                    .stream().collect(Collectors.toSet());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptySet();
    }

    @Override
    public void update(CustomersDao entity) {
        try (Session session = manager.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
