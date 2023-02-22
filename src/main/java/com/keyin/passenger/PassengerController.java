package com.keyin.passenger;

import com.keyin.aircraft.Aircraft;
import com.keyin.city.City;
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

    @GetMapping("/passengers")
    public List<Passenger> getAllPassengers() {
        return (List<Passenger>) repo.findAll();
    }

    @PostMapping("/passenger")
    public void createPassenger(@RequestBody Passenger passenger) {
        repo.save(passenger);
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
            passengerToUpdate.setCity(passenger.getCity());
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
