package com.keyin.aircraft;

import com.keyin.passenger.Passenger;
import com.keyin.airport.Airport;

import javax.persistence.*;
import java.util.List;

// Class Aircraft is an entity class and is mapped to the table aircraft in the database
@Entity
public class Aircraft {
    // @Id annotation is used to specify the primary key of an entity and @SequenceGenerator is used to generate the primary key value automatically
    // @GeneratedValue is used to specify the type of generation strategy is used to generate the primary key value
    @Id
    @SequenceGenerator(name = "aircraft_sequence", sequenceName = "aircraft_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "aircraft_sequence")
    private long id;
    // instance variables of the class Aircraft
    private String type;
    private String airlineName;
    private int numberOfPassengers;

    // relationships between the entity classes Aircraft and Airport and Aircraft and Passenger
    // NOTE: Why is there no toString() method for the relationship between Aircraft, Airport and Passenger?
    @ManyToMany
    private List<Airport> airports;

    @ManyToMany
    private List<Passenger> passengers;

    public Aircraft() {
    }
    // Below are the getters and setters for the instance variables of the class Aircraft
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public List<Airport> getAirports() {
        return (List<Airport>) airports;
    }
    public List <Passenger> getPassengers() {
        return (List<Passenger>) passengers;
    }



}
