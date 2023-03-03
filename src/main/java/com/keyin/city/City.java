package com.keyin.city;

import com.keyin.airport.Airport;
import com.keyin.passenger.Passenger;

import javax.persistence.*;
import java.util.List;

// Class City is an entity class and is mapped to the table city in the database
@Entity
public class City {
    // @Id annotation is used to specify the primary key of an entity and @SequenceGenerator is used to generate the primary key value automatically
    // @GeneratedValue is used to specify the type of generation strategy is used to generate the primary key value
    @Id
    @GeneratedValue(generator = "city_sequence")
    @SequenceGenerator(name = "city_sequence", sequenceName = "city_sequence", allocationSize = 1, initialValue = 1)
    // instance variables of the class City
    private Long id;
    private String name;
    private String state;
    private int population;

    // relationships between the entity classes City and Airport and City and Passenger
    // NOTE: Why is there no toString() method for the relationship between Aircraft, Airport and Passenger?
    @OneToMany
    private List<Airport> airports;

    @OneToMany
    private List<Passenger> passengers;
    // default constructor
    public City() {
    }

    // Below are the getters and setters for the instance variables of the class City
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return name;
    }

    public void setCityName(String name) {
        this.name = name;
    }

    public String getCityState() {
        return state;
    }

    public void setCityState(String state) {
        this.state = state;
    }

    public int getCityPopulation() {
        return population;
    }

    public void setCityPopulation(int population) {
        this.population = population;
    }

    public List<Airport> getAirports() {
        return (List<Airport>) airports;
    }

    public List <Passenger> getPassengers() {
        return (List<Passenger>) passengers;
    }


}
