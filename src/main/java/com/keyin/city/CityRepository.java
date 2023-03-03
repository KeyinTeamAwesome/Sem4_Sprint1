package com.keyin.city;

// Java repositories are also known as JPAs (Java Persistence API) based repositories used under Java spring framework.
// @Repository annotation is used to indicate that the class provides the mechanism for storage, retrieval, search, update and delete operation on objects.
import com.keyin.aircraft.Aircraft;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository
        extends CrudRepository<City, Long> {

    public List<City> findByAirports_airportName(String airportName);

}