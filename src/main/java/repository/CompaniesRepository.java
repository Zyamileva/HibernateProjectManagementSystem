package repository;

import config.HibernateProvider;
import model.dao.CompaniesDao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.*;
import java.util.stream.Collectors;

public class CompaniesRepository implements Repository<CompaniesDao> {
    private final HibernateProvider manager;

    public CompaniesRepository(HibernateProvider manager) {
        this.manager = manager;
    }

    @Override
    public CompaniesDao save(CompaniesDao entity) {
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
    public void delete(CompaniesDao entity) {
        try (Session session = manager.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<CompaniesDao> findByName(String name) {
        try (Session session = manager.openSession()) {
            return Optional.ofNullable(session.createQuery("FROM CompaniesDao as companies WHERE companies.name = :name",
                            CompaniesDao.class)
                    .setParameter("name", name).getSingleResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<CompaniesDao> findById(int id) {
        try (Session session = manager.openSession()) {
            return Optional.ofNullable(session.createQuery("FROM CompaniesDao as companies WHERE companies.id = :id",
                    CompaniesDao.class).setParameter("id", id).getSingleResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Set<CompaniesDao> findAll() {
        try (Session session = manager.openSession()) {
            return session.createQuery("select companies FROM CompaniesDao as companies", CompaniesDao.class)
                    .stream().collect(Collectors.toSet());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptySet();
    }

    @Override
    public void update(CompaniesDao entity) {
        try (Session session = manager.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
