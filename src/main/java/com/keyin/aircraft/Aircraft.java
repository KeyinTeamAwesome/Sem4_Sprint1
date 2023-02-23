package com.keyin.aircraft;

import com.keyin.passenger.Passenger;
import com.keyin.airport.Airport;

import javax.persistence.*;
import java.util.List;

@Entity
public class Aircraft {

    @Id
    @SequenceGenerator(name = "aircraft_sequence", sequenceName = "aircraft_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "aircraft_sequence")
    private long id;

    private String type;
    private String airlineName;
    private int numberOfPassengers;


    @ManyToMany
    private List<Airport> airports;

    @ManyToMany
    private List<Passenger> passengers;

    public Aircraft() {
    }

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
