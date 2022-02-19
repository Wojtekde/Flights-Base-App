package project.sda.domain.flight;

import project.sda.domain.user.User;
import project.sda.domain.user.UserService;

import java.util.List;

public class FlightService {

    private final FlightDao flightDao;
    private final UserService userService;

    public FlightService(FlightDao flightDao, UserService userService) {
        this.flightDao = flightDao;
        this.userService = userService;
    }

    public List<Flight> getAll() {
        return flightDao.findAll();
    }


    public void create(Flight flight) {
        flightDao.save(flight);
    }

    public void remove(int id) {
        flightDao.deleteById(id);
    }

    public void book(int userId, int flightId) {
        Flight flight = flightDao.findOne(flightId);
        User user = userService.findOne(userId);
        flight.addUser(user);
        flightDao.update(flight);
    }

    public void cancelForUser(int userId, int flightId) {
        Flight flight = flightDao.findOne(flightId);
        User user = userService.findOne(userId);
        flight.removeUser(user);
        flightDao.update(flight);
    }

    public List<Flight> getAllAvailable(int userId) {
        User user = userService.findOne(userId);
        return flightDao.findAllAvailable(user);
    }

}
