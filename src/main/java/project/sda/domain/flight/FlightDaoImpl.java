package project.sda.domain.flight;

import project.sda.domain.user.User;
import project.sda.infrastructure.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlightDaoImpl implements FlightDao {

    private final EntityManager entityManager;

    public FlightDaoImpl() {
        this.entityManager = HibernateUtil.getSessionFactory().createEntityManager();
    }

    @Override
    public void save(Flight entity) {
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
    public void update(Flight entity) {
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
        // TODO - remove entity, first you have to find object in database by id and then remove from db
        // entityManager.remove(ENTITY);
    }

    @Override
    public Flight findOne(int id) {
        return entityManager.find(Flight.class, id);
    }

    @Override
    public Optional<Flight> findOneOptional(int id) {
        return Optional.ofNullable(entityManager.find(Flight.class, id));
    }

    @Override
    public List<Flight> findAll() {
        return entityManager.createQuery("FROM Flight", Flight.class).getResultList();
    }

    @Override
    public List<Flight> findAllAvailable(User user) {
        List<Flight> flights = findAll();
        List<Flight> availableFlights = flights.stream()
                .filter(flight -> flight.isPlaceAvailable() && !flight.isUserOnThePlane(user))
                .collect(Collectors.toList());
        return availableFlights;
    }
}
