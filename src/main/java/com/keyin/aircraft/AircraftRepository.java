package com.keyin.aircraft;

// Java repositories are also known as JPAs (Java Persistence API) based repositories used under Java spring framework
// @Repository annotation is used to indicate that the class provides the mechanism for storage, retrieval, search, update and delete operation on objects
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface AircraftRepository
        extends CrudRepository<Aircraft, Long> {

    public List<Aircraft> findByAirports_airportName(String airportName);
    public List<Aircraft> findByPassengers_lastName(String lastName);

}