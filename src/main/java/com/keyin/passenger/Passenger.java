package com.keyin.passenger;

// import javax.persistence.* is used to specify the mapping between the entity classes and the database tables
import javax.persistence.*;


// Class Passenger is an entity class and is mapped to the table passenger in the database.
@Entity
public class Passenger {
    // @Id annotation, is used to specify the primary key of an entity and @SequenceGenerator is used to generate the primary key value automatically.
    // @GeneratedValue is used to specify the type of generation strategy is used to generate the primary key value.
    @Id
    @SequenceGenerator(name = "passenger_sequence", sequenceName = "passenger_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "passenger_sequence")
    // instance variables of the class Passenger
    private long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    // Default constructor and has to have no arguments passed to it.
    public Passenger() {
    }
    // Below are the getters and setters for the instance variables of the class Passenger.
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    // toString() method is used to return the firstName and lastName of the passenger.
    public String toString(){
        return this.firstName + " " + this.lastName;
    }

}
