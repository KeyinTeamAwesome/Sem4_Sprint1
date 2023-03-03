package com.keyin.airport;

// imports that are greyed out are not used in this class and the only import needed is passenger.Passenger
import com.keyin.city.City;
import com.keyin.aircraft.Aircraft;
import com.keyin.passenger.Passenger;
// import javax.persistence.* is used to specify the mapping between the entity classes and the database tables
import javax.persistence.*;
// import java.util.List is used to import the List interface
import java.util.List;

// Class Airport is an entity class and is mapped to the table airport in the database
@Entity
public class Airport {
    // @Id annotation is used to specify the primary key of an entity and @SequenceGenerator is used to generate the primary key value automatically
    // @GeneratedValue is used to specify the type of generation strategy is used to generate the primary key value
    @Id
    @SequenceGenerator(name = "airport_sequence", sequenceName = "airport_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "airport_sequence")
    // private long id, String airportName, String airportCode are instance variables of the class Airport
    private long id;
    private String airportName;
    private String airportCode; // IATA code is used to identify the airport

    // @ManyToMany annotation is used to specify the many-to-many relationship between the entity classes Airport and Passenger
    @ManyToMany
    private List<Passenger> passengers;

    // public Airport() is a default constructor and has to have no arguments passed to it
    public Airport() {
    }

    // Below are the getters and setters for the instance variables of the class Airport
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

    // toString() method is used to return the airportName of the airport
    public String toString(){
        return this.airportName;
    }

}
