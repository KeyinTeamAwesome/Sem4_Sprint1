package com.keyin.city;

import com.keyin.aircraft.Aircraft;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository
        extends CrudRepository<City, Long> {

    public List<City> findByAirports_airportName(String airportName);

}