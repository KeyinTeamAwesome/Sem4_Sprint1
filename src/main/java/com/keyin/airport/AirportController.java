package com.keyin.airport;

import com.keyin.aircraft.Aircraft;
import com.keyin.aircraft.AircraftRepository;
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
public class AirportController {
    @Autowired
    private AirportRepository repo;

    @Autowired
    private CityRepository cityRepo;


    @Autowired
    private AircraftRepository aircraftRepo;

    @GetMapping("/airports")
    public List<Airport> getAllAirports() {
        return (List<Airport>) repo.findAll();
    }


    @GetMapping("/airport/{id}")
    public Optional<Airport> getAirportById(@PathVariable Long id) {
        return repo.findById(id);
    }

    @PostMapping("/airport")
    public void createAirport(@RequestBody AirportOTA airportOTA) {

        Airport newAirport = new Airport();

        Optional<City> returnValue = cityRepo.findById(airportOTA.getCityId());
        Optional<Aircraft> returnAircraftValue = aircraftRepo.findById(airportOTA.getAircraftId());

        newAirport.setAirportCode(airportOTA.getCode());
        newAirport.setAirportName(airportOTA.getName());
        repo.save(newAirport);

        City city = returnValue.get();
        Aircraft aircraft = returnAircraftValue.get();

        city.getAirports().add(newAirport);
        cityRepo.save(city);
        aircraft.getAirports().add(newAirport);
        aircraftRepo.save(aircraft);

    }


    @PutMapping("/airport/{id}")
    public void updateAirport(@PathVariable String id, @RequestBody Airport airport, HttpServletResponse response) {
        Optional<Airport> returnValue = repo.findById(Long.parseLong(id));
        Airport airportToUpdate;

        if (returnValue.isPresent()) {
            airportToUpdate = returnValue.get();

            airportToUpdate.setAirportName(airport.getAirportName());
            airportToUpdate.setAirportCode(airport.getAirportCode());

            repo.save(airportToUpdate);
        } else {
            try {
                response.sendError(404, "Airport with id: " + airport.getId() + " not found.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @DeleteMapping("/airport/{id}")
    public void deleteAirport(@PathVariable String id, HttpServletResponse response) {
        Optional<Airport> returnValue = repo.findById(Long.parseLong(id));

        if (returnValue.isPresent()) {
            repo.deleteById(Long.parseLong(id));
        } else {
            try {
                response.sendError(404, "Airport with id: " + id + " not found.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}