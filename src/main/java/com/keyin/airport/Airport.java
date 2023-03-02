package com.keyin.airport;

import com.keyin.city.City;
import com.keyin.aircraft.Aircraft;
import com.keyin.passenger.Passenger;

import javax.persistence.*;
import java.util.List;

// Class Airport is an entity class and is mapped to the table airport in the database
@Entity
public class Airport {
    // @Id annotation is used to specify the primary key of an entity and @SequenceGenerator is used to generate the primary key value automatically
    // @GeneratedValue is used to specify the type of generation strategy is used to generate the primary key value
    @Id
    @SequenceGenerator(name = "airport_sequence", sequenceName = "airport_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "airport_sequence")
    private long id;
    private String airportName;
    private String airportCode;

    @ManyToMany
    private List<Passenger> passengers;


    public Airport() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public List <Passenger> getPassengers() {
        return (List<Passenger>) passengers;
    }

    public String toString(){
        return this.airportName;
    }

}
