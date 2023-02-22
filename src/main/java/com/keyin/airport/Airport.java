package com.keyin.airport;

import com.keyin.city.City;
import com.keyin.aircraft.Aircraft;

import javax.persistence.*;
import java.util.List;

@Entity
public class Airport {
    @Id
    @SequenceGenerator(name = "airport_sequence", sequenceName = "airport_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "airport_sequence")
    private long id;
    private String airportName;
    private String airportCode;

    @ManyToOne
    private City city;

    @OneToMany
    private List<Aircraft> aircraft;


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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Aircraft> getAircraft() {
        return (List<Aircraft>) aircraft;
    }

    public void setAircraft(List<Aircraft> aircraft) {
        this.aircraft = aircraft;
    }

//    public Aircraft getAircraft() {
//        return aircraft;
//    }
//
//    public void setAircraft(Aircraft aircraft) {
//        this.aircraft = aircraft;
//    }
}