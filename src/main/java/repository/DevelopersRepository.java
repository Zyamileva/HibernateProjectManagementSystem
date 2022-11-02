package repository;

import config.HibernateProvider;
import model.dao.DevelopersDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.*;
import java.util.stream.Collectors;

public class DevelopersRepository implements Repository<DevelopersDao> {
    private final HibernateProvider manager;

    public DevelopersRepository(HibernateProvider manager) {
        this.manager = manager;
    }

    @Override
    public DevelopersDao save(DevelopersDao entity) {
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
    public void delete(DevelopersDao entity) {
        try (Session session = manager.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<DevelopersDao> findByName(String name) {
        try (Session session = manager.openSession()) {
            return Optional.ofNullable(session.createQuery("FROM DevelopersDao as developers WHERE developers.lastName = :name",
                            DevelopersDao.class)
                    .setParameter("name", name).getSingleResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Set<DevelopersDao> findByNameSet(String name) {
        try (Session session = manager.openSession()) {
            return session.createQuery("FROM DevelopersDao as developers WHERE developers.lastName = :name",
                            DevelopersDao.class)
                    .setParameter("name", name).stream().collect(Collectors.toSet());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptySet();
    }

    @Override
    public Optional<DevelopersDao> findById(int id) {
        try (Session session = manager.openSession()) {
            return Optional.ofNullable(session.createQuery("FROM DevelopersDao as developers WHERE developers.id = :id",
                            DevelopersDao.class)
                    .setParameter("id", id).getSingleResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Set<DevelopersDao> findAll() {
        try (Session session = manager.openSession()) {
            Transaction transaction = session.beginTransaction();
            Set<DevelopersDao> from_developersDao_as_developers = session.createQuery("FROM DevelopersDao as developers", DevelopersDao.class)
                    .stream().collect(Collectors.toSet());
            transaction.commit();
            return from_developersDao_as_developers;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptySet();
    }

    @Override
    public void update(DevelopersDao entity) {
        try (Session session = manager.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveSkills(int idDeveloper, int idNameLevel) {
        try (Session session = manager.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery("INSERT INTO developers_skills(developer_id, skill_id) VALUES(?1, ?2)")
                    .setParameter(1, idDeveloper).setParameter(2, idNameLevel).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Set<Integer> findDevelopersOfProject(int projectId) {
        try (Session session = manager.openSession()) {
            return (Set<Integer>) session.createNativeQuery("select dp.developer_id from developers_projects as dp where dp.project_id = ?1")
                    .setParameter(1, projectId).stream().collect(Collectors.toSet());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptySet();
    }

    public void deleteDeveloperOfIdsSkill(int skillId) {
        try (Session session = manager.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery("delete from developers_skills as ds where ds.skill_id =?1")
                    .setParameter(1, skillId).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
