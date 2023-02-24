package com.keyin.airport;

import com.keyin.passenger.Passenger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository
        extends CrudRepository<Airport, Long> {

    public List<Airport> findByPassengers_lastName(String lastName);
}
