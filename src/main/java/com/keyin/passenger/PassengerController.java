package com.keyin.passenger;

import com.keyin.aircraft.Aircraft;
import com.keyin.aircraft.AircraftRepository;
import com.keyin.city.City;
import com.keyin.city.CityRepository;
import com.keyin.airport.Airport;
import com.keyin.airport.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.Option;
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

    @Autowired
    private AircraftRepository aircraftRepo;

    @Autowired
    private AirportRepository airportRepo;

    @GetMapping("/passengers")
    public List<Passenger> getAllPassengers() {
        return (List<Passenger>) repo.findAll();
    }

    @GetMapping("/passenger/{id}")
    public Optional<Passenger> getPassengerById(@PathVariable Long id) {
        return repo.findById(id);
    }

    @PostMapping("/passenger")
    public void createPassenger(@RequestBody PassengerOTA passengerOTA) {

        Passenger newPassenger = new Passenger();

        Optional<City> returnValue = cityRepo.findById(passengerOTA.getCityId());
        Optional<Aircraft> returnAircraftValue = aircraftRepo.findById(passengerOTA.getAircraftId());
        Optional<Airport> returnAirportValue = airportRepo.findById(passengerOTA.getAirportId());


        newPassenger.setLastName(passengerOTA.getLastName());
        newPassenger.setFirstName(passengerOTA.getFirstName());
        newPassenger.setPhoneNumber(passengerOTA.getPhoneNumber());

        repo.save(newPassenger);

        City city = returnValue.get();
        Aircraft aircraft = returnAircraftValue.get();
        Airport airport = returnAirportValue.get();


        city.getPassengers().add(newPassenger);
        aircraft.getPassengers().add(newPassenger);
        airport.getPassengers().add(newPassenger);
        cityRepo.save(city);
        aircraftRepo.save(aircraft);
        airportRepo.save(airport);

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
