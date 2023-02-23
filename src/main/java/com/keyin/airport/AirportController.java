package com.keyin.airport;

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

    @GetMapping("/airports")
    public List<Airport> getAllAirports() {
        return (List<Airport>) repo.findAll();
    }


    @GetMapping("/airport/{id}")
    public Optional<Airport> getAirportById(@PathVariable Long id) {
        return repo.findById(id);
    }

//    @PostMapping("/airport")
//    public void createAirport(@RequestBody AirportOTA airportOTA) {
//
//        Airport newAirport = new Airport();
//
//        Optional<City> returnValue = cityRepo.findById(airportOTA.getCityId());
//
//        newAirport.setCity(returnValue.get());
//        newAirport.setAirportCode(airportOTA.getCode());
//        newAirport.setAirportName(airportOTA.getName());
//        repo.save(newAirport);
//    }

    @PostMapping("/airport")
    public void createAirport(@RequestBody Airport airport) {
        repo.save(airport);
    }

    @PutMapping("/airport/{id}")
    public void updateAirport(@PathVariable String id, @RequestBody Airport airport, HttpServletResponse response) {
        Optional<Airport> returnValue = repo.findById(Long.parseLong(id));
        Airport airportToUpdate;

        if (returnValue.isPresent()) {
            airportToUpdate = returnValue.get();

            airportToUpdate.setAirportName(airport.getAirportName());
            airportToUpdate.setAirportCode(airport.getAirportCode());
            airportToUpdate.setCity(airport.getCity());
            airportToUpdate.setAircraft(airport.getAircraft());

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