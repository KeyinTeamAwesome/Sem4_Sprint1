package com.keyin.aircraft;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface AircraftRepository
        extends CrudRepository<Aircraft, Long> {

    public List<Aircraft> findByAirports_airportName(String airportName);
    public List<Aircraft> findByPassengers_lastName(String lastName);

}