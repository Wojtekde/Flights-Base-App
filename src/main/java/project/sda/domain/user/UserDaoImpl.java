package project.sda.domain.user;

import project.sda.infrastructure.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private final EntityManager entityManager;

    public UserDaoImpl() {
        this.entityManager = HibernateUtil.getSessionFactory().createEntityManager();
    }

    @Override
    public void save(User entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public void update(User entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(entity);
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public void deleteById(int id) {
        Optional<User> findOneOptional = findOneOptional(id);
        if (findOneOptional.isPresent()) {
            entityManager.remove(findOneOptional.get());
        }
    }

    @Override
    public User findOne(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public Optional<User> findOneOptional(int id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public User findByUsernameAndPassword(String usernameParam, String passwordParam) {
        try {

            // select * from users where username = 'wprowdzony_login' and password = 'wprowadzony_password';
            return entityManager.createQuery("FROM User where username = :usernameParam and password = :passwordParam", User.class)
                    .setParameter("usernameParam", usernameParam)
                    .setParameter("passwordParam", passwordParam).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User findByUsername(String usernameParam) {
        try {
            // select * from users where username = 'wprowdzony_login' and password = 'wprowadzony_password';
            return entityManager.createQuery("FROM User where username = :usernameParam", User.class)
                    .setParameter("usernameParam", usernameParam)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}

