package repository;

import config.HibernateProvider;
import model.dao.DevelopersDao;
import model.dao.ProjectsDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.*;
import java.util.stream.Collectors;

public class ProjectsRepository implements Repository<ProjectsDao> {
    private final HibernateProvider manager;

    public ProjectsRepository(HibernateProvider manager) {
        this.manager = manager;
    }

    @Override
    public ProjectsDao save(ProjectsDao entity) {
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
    public void delete(ProjectsDao entity) {
        try (Session session = manager.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<ProjectsDao> findByName(String name) {
        try (Session session = manager.openSession()) {
            return Optional.ofNullable(session.createQuery("FROM ProjectsDao as projects WHERE projects.name = :name",
                            ProjectsDao.class)
                    .setParameter("name", name).getSingleResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<ProjectsDao> findById(int id) {
        try (Session session = manager.openSession()) {
            return Optional.ofNullable(session.createQuery("FROM ProjectsDao as projects WHERE projects.id = :id",
                            ProjectsDao.class)
                    .setParameter("id", id).getSingleResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Set<ProjectsDao> findAll() {
        try (final Session session = manager.openSession()) {
            return session.createQuery("FROM ProjectsDao as projects", ProjectsDao.class)
                    .stream().collect(Collectors.toSet());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptySet();
    }

    public void deleteOfIdsProject(int id) {
        try (Session session = manager.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery("delete from developers_projects as pr where pr.project_id =?1")
                    .setParameter(1, id).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ProjectsDao entity) {
        try (Session session = manager.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveDeveloper(int idDeveloper, int idProject) {
        try (Session session = manager.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery("insert into developers_projects (developer_id, project_id) values (?1,?2) ")
                    .setParameter(1, idDeveloper).setParameter(2, idProject).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteProjectOfIdsDeveloper(int developerId) {
        try (Session session = manager.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery("delete from developers_projects where developer_id = ?1")
                    .setParameter(1, developerId).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteOfIdsDeveloperOfProject(int developerId, int projectId) {
        try (Session session = manager.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery("delete from developers_projects where developer_id = ?1 and project_id = ?2")
                    .setParameter(1, developerId).setParameter(2, projectId).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
