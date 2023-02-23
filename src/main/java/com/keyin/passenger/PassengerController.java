package com.keyin.passenger;

import com.keyin.aircraft.Aircraft;
import com.keyin.airport.Airport;
import com.keyin.airport.AirportOTA;
import com.keyin.airport.AirportRepository;
import com.keyin.city.City;
import com.keyin.city.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class PassengerController {
    @Autowired
    private PassengerRepository repo;

    @Autowired
    private CityRepository cityRepo;

    @GetMapping("/passengers")
    public List<Passenger> getAllPassengers() {
        return (List<Passenger>) repo.findAll();
    }

    @PostMapping("/passenger")
    public void createPassenger(@RequestBody PassengerOTA passengerOTA) {

        Passenger newPassenger = new Passenger();

        Optional<City> returnValue = cityRepo.findById(passengerOTA.getCityId());

        newPassenger.setLastName(passengerOTA.getLastName());
        newPassenger.setFirstName(passengerOTA.getFirstName());
        newPassenger.setPhoneNumber(passengerOTA.getPhoneNumber());


        repo.save(newPassenger);

        City city = returnValue.get();

        city.getPassengers().add(newPassenger);
        cityRepo.save(city);

    }

    @PutMapping("/passenger/{id}")
    public void updatePassenger(@PathVariable String id, @RequestBody Passenger passenger, HttpServletResponse response) {
        Optional<Passenger> returnValue = repo.findById(Long.parseLong(id));
        Passenger passengerToUpdate;

        if (returnValue.isPresent()) {
            passengerToUpdate = returnValue.get();

            passengerToUpdate.setFirstName(passenger.getFirstName());
            passengerToUpdate.setLastName(passenger.getLastName());
            passengerToUpdate.setPhoneNumber(passenger.getPhoneNumber());
            passengerToUpdate.setAircraft(passenger.getAircraft());

            repo.save(passengerToUpdate);
        } else {
            try {
                response.sendError(404, "Passenger with id: " + passenger.getId() + " not found.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        }
    @DeleteMapping("/passenger/{id}")
    public void deletePassenger(@PathVariable String id, HttpServletResponse response) {
        Optional<Passenger> returnValue = repo.findById(Long.parseLong(id));

        if (returnValue.isPresent()) {
            repo.deleteById(Long.parseLong(id));
        } else {
            try {
                response.sendError(404, "Passenger with id: " + id + " not found.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    }
