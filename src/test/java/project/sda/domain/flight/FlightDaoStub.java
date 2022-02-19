package project.sda.domain.flight;

import project.sda.domain.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlightDaoStub implements FlightDao {

    private final List<Flight> flights = new ArrayList<>();


    @Override
    public void save(Flight entity) {
        Optional<Integer> currentId = flights.stream().map(Flight::getId).distinct().findFirst();
        entity.setId(currentId.map(id -> id + 1).orElse(1));

        flights.add(entity);
    }

    @Override
    public void update(Flight entity) {
        // TODO
    }

    @Override
    public void deleteById(int id) {
        Optional<Flight> userOptional = flights.stream().filter(user -> user.getId().equals(id)).findFirst();
        userOptional.ifPresent(flights::remove);
    }

    @Override
    public Flight findOne(int id) {
        return findOneOptional(id).orElse(null);
    }

    @Override
    public Optional<Flight> findOneOptional(int id) {
        return flights.stream().filter(u -> u.getId().equals(id)).findFirst();
    }

    @Override
    public List<Flight> findAll() {
        return flights;
    }

    @Override
    public List<Flight> findAllAvailable(User user) {
        List<Flight> flights = findAll();
        return flights.stream()
                .filter(flight -> flight.isPlaceAvailable() && !flight.isUserOnThePlane(user))
                .collect(Collectors.toList());
    }
}
