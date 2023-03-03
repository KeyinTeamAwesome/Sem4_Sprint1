package com.keyin.airport;

// Java repositories are also known as JPAs (Java Persistence API) based repositories used under Java spring framework.
// @Repository annotation is used to indicate that the class provides the mechanism for storage, retrieval, search, update and delete operation on objects.
import com.keyin.aircraft.Aircraft;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository
        extends CrudRepository<Airport, Long> {
    public List<Airport> findByPassengers_lastName(String lastName);
}
