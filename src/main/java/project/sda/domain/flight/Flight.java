package project.sda.domain.flight;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;

@Entity
@Table(name="flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name= "source_city")
    private String sourceCity;
    @Column(name = "destination_city")
    private String destinationCity;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "number_of_seats")
    private Integer numberOfSeatsInPlane;

    public Flight(){

    }

    public Flight(String sourceCity, String destinationCity, LocalDate date, Integer numberOfSeatsInPlane) {
        this.sourceCity = sourceCity;
        this.destinationCity = destinationCity;
        this.date = date;
        this.numberOfSeatsInPlane = numberOfSeatsInPlane;
    }

    public Integer getId() {
        return id;
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
}
