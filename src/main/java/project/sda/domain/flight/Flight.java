package project.sda.domain.flight;

import project.sda.domain.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "source_city")
    @NotNull
    private String sourceCity;

    @Column(name = "destination_city")
    @NotNull
    private String destinationCity;

    @Column(name = "date")
    @NotNull
    private LocalDate date;

    @Column(name = "number_of_seats")
    @NotNull
    private Integer numberOfSeatsInPlane;

    @ManyToMany(fetch = FetchType.EAGER)
    private final List<User> users = new ArrayList<>();

    public Flight() {

    }

    public Flight(String sourceCity, String destinationCity, LocalDate date, Integer numberOfSeatsInPlane) {
        this.sourceCity = sourceCity;
        this.destinationCity = destinationCity;
        this.date = date;
        this.numberOfSeatsInPlane = numberOfSeatsInPlane;
    }

    public int getFreePlaces() {
        return numberOfSeatsInPlane - users.size();
    }

    public boolean isPlaceAvailable() {
        return getFreePlaces() > 0;
    }

    public boolean isUserOnThePlane(User user) {
        return users.contains(user);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSourceCity() {
        return sourceCity;
    }

    public void setSourceCity(String sourceCity) {
        this.sourceCity = sourceCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getNumberOfSeatsInPlane() {
        return numberOfSeatsInPlane;
    }

    public void setNumberOfSeatsInPlane(Integer numberOfSeatsInPlane) {
        this.numberOfSeatsInPlane = numberOfSeatsInPlane;
    }

    public List<User> getUsers() {
        return users;
    }

    void addUser(User user) {
        users.add(user);
    }

    void removeUser(User user) {
        users.remove(user);
    }
}
