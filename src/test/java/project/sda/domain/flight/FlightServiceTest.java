package project.sda.domain.flight;

import org.junit.Before;
import org.junit.Test;
import project.sda.domain.user.UserDaoStub;
import project.sda.domain.user.UserService;

public class FlightServiceTest {

    private FlightService flightService;

    @Before
    public void setUp() throws Exception {
        flightService = new FlightService(new FlightDaoStub(), new UserService(new UserDaoStub()));
    }

    @Test
    void shouldCreateNewFlight() {
        // TODO
    }


    @Test
    void shouldRemoveFlight() {
        // TODO
    }

    @Test
    void shouldBookForUser() {
        // TODO
    }

    @Test
    void shouldCancelForUser() {
        // TODO
    }

    @Test
    void shouldReturnAvailableFlightsForUser() {
        // TODO
    }
}
