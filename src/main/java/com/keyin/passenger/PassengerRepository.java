package com.keyin.passenger;

import com.keyin.city.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengerRepository
        extends CrudRepository<Passenger, Long> {
}
