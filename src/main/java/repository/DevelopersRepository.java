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
            Query<DevelopersDao> query = session.createQuery("FROM DevelopersDao as developers", DevelopersDao.class);
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

    public List<DevelopersDao> listOfSkillNameDevelopers(String skillName) {
//        final String query = """
//                select d.*
//                from developers as d
//                join developers_skills as ds ON ds.developer_id = d.id
//                join skills as s on s.id = ds.skill_id and s.name = ?
//                """;
//        List<DevelopersDao> developers = new ArrayList<>();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1, skillName);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                developers.add(developersMapper.map(resultSet));
//            }
//            return developers;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }

    public List<DevelopersDao> listOfSkillLevelDevelopers(String skillLevel) {
//        final String query = """
//                select d.*
//                from developers as d
//                join developers_skills as ds ON ds.developer_id = d.id
//                join skills as s on s.id = ds.skill_id and s.level = ?
//                """;
//        List<DevelopersDao> developers = new ArrayList<>();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1, skillLevel);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                developers.add(developersMapper.map(resultSet));
//            }
//            return developers;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }

    public List<Integer> listSkillsOfDevelopers(int idDeveloper) {
//        final String query = """
//                select d.skill_id
//                from developers_skills as d
//                where d.developer_id = ?
//                """;
//        List<Integer> skills = new ArrayList<>();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1, idDeveloper);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                skills.add(resultSet.getInt(1));
//            }
//            return skills;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }

    public void saveSkills(int idDeveloper, int idNameLevel) {
//        final String INSERT = "INSERT INTO developers_skills(developer_id, skill_id)" +
//                " VALUES(?,?)";
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
//            preparedStatement.setInt(1, idDeveloper);
//            preparedStatement.setInt(2, idNameLevel);
//            preparedStatement.execute();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void updateSkills(int idDeveloper, int idNameLevel, int id) {
//        final String query = """
//                update developers_skills set
//                developer_id = ?,
//                skill_id = ?,
//                where id = ?
//                """;
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1, idDeveloper);
//            preparedStatement.setInt(2, idNameLevel);
//            preparedStatement.setInt(3, id);
//            preparedStatement.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public void deleteSkillsOfDeveloper(int developerId) {
//        final String query = """
//                delete from developers_skills
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

    public void deleteDeveloperOfIdsSkill(int skillId) {
//        final String query = """
//                delete from developers_skills
//                where skill_id = ?
//                """;
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1, skillId);
//            preparedStatement.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
