package repository;

import config.HibernateProvider;
import model.dao.SkillsDao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.*;
import java.util.stream.Collectors;

public class SkillsRepository implements Repository<SkillsDao> {
    private final HibernateProvider manager;

    public SkillsRepository(HibernateProvider manager) {
        this.manager = manager;
    }

    @Override
    public SkillsDao save(SkillsDao entity) {
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
    public void delete(SkillsDao entity) {
        try (Session session = manager.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Set<SkillsDao> findByNameSet(String name) {
//        final String FIND_BY_NAME = "SELECT * FROM skills WHERE name = ?";
//        Set<SkillsDao> skills = new HashSet<>();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME);
//            preparedStatement.setString(1, name);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                skills.add(skillsMapper.map(resultSet));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return skills;
        return null;
    }

    @Override
    public Optional<SkillsDao> findByName(String name) {
        try (Session session = manager.openSession()) {
            return Optional.ofNullable(session.createQuery("FROM SkillsDao as skills WHERE skills.name = :name",
                            SkillsDao.class)
                    .setParameter("name", name).getSingleResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<SkillsDao> findById(int id) {
        try (Session session = manager.openSession()) {
            return Optional.ofNullable(session.createQuery("FROM SkillsDao as skills WHERE skills.id = :id",
                            SkillsDao.class)
                    .setParameter("id", id).getSingleResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
        @Override
        public Set<SkillsDao> findAll () {
            try (final Session session = manager.openSession()) {
                return session.createQuery("FROM SkillsDao as skills", SkillsDao.class)
                        .stream().collect(Collectors.toSet());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Collections.emptySet();
        }
        @Override
        public void update (SkillsDao entity){
            try (Session session = manager.openSession()) {
                Transaction transaction = session.beginTransaction();
                session.update(entity);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public int findByNameLevel (String name, String level){
//        final String EXIST = "SELECT id FROM skills WHERE name = ? AND level = ? ";
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(EXIST);
//            preparedStatement.setString(1, name);
//            preparedStatement.setString(2, level);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                return resultSet.getInt("id");
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
            return 0;
        }
    }
