package project.sda.domain.flight;

import java.util.List;
import java.util.Optional;

public interface FlightDao {

    void save(Flight entity);

    void update(Flight entity);

    void deleteById(int id);

    Flight findOne(int id);

    Optional<Flight> findOneOptional(int id);

    List<Flight> findAll();
}
