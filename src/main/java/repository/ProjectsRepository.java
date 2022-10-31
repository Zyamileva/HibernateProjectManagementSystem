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

    public int sallaryOfProjects(int id) {
//        final String query = """
//                select SUM(salary) as all_sallary, pr.name as name
//                from projects as pr
//                join developers_projects as dp on pr.id=dp.project_id
//                join developers as d on dp.developer_id=d.id
//                where pr.id=?
//                group by name
//                """;
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                return resultSet.getInt(1);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return 0;
    }

    public List<DevelopersDao> ListDevelopersOfProjects(int id) {
//        final String query = """
//                select d.*
//                from projects as pr
//                join developers_projects as dp on pr.id=dp.project_id
//                join developers as d on dp.developer_id=d.id
//                where pr.id=?
//                """;
//        List<DevelopersDao> developers = new ArrayList<>();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                developers.add(developersMapper.map(resultSet));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return developers;
        return null;
    }

    public int CountDevelopersOfProjects(int id) {
//        final String query = """
//                select count(d.id)
//                from projects as pr
//                join developers_projects as dp on pr.id=dp.project_id
//                join developers as d on dp.developer_id=d.id
//                where pr.id=?
//                """;
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                return resultSet.getInt(1);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return 0;
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

    public boolean findByIdDeveloperIdProjects(int idDeveloper, int idProject) {
//        final String FIND_BY_ID = "SELECT * FROM developers_projects WHERE developer_id = ? and project_id = ?";
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
//            preparedStatement.setInt(1, idDeveloper);
//            preparedStatement.setInt(2, idProject);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                return true;
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return false;
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

    public void deleteProjectOfIdsDeveloper(int developerId) {
//        final String query = """
//                delete from developers_projects
//                where developer_id = ?
//                """;
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1, developerId);
//            preparedStatement.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public void deleteOfIdsDeveloperOfProject(int developerId, int projectId) {
//        final String query = """
//                delete from developers_projects
//                where developer_id = ? and project_id = ?
//                """;
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1, developerId);
//            preparedStatement.setInt(2, projectId);
//            preparedStatement.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
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
//        final String INSERT = "INSERT INTO developers_projects(developer_id, project_id)" +
//                " VALUES(?,?)";
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
//            preparedStatement.setInt(1, idDeveloper);
//            preparedStatement.setInt(2, idProject);
//            preparedStatement.execute();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
}
