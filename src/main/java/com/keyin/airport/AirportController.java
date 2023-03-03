package com.keyin.airport;

// AirportController.java this is a controller class and is used to handle the HTTP requests and responses

// aircraft.Aircraft is used to import the Aircraft class, aircraft.AircraftRepository is used to import the AircraftRepository interface
// this is similar for city.City and city.CityRepository
import com.fasterxml.jackson.core.JsonProcessingException;
import com.keyin.aircraft.Aircraft;
import com.keyin.aircraft.AircraftRepository;
import com.keyin.city.City;
import com.keyin.city.CityRepository;
// net.minidev.json.JSONObject is used to import the JSONObject class from the json-smart library
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// the imports below are used to import the classes and interfaces that are used in the class AirportController
// without the javax.servlet.http.HttpServletResponse import, the HttpServletResponse response parameter in the updateAirport method will not work
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// @RestController annotation is used to create RESTful web services using Spring MVC
// @CrossOrigin annotation is used to allow cross-origin requests
// cross-origin requests are requests that are made from a different domain than the domain of the resource being requested
@RestController
@CrossOrigin
public class AirportController {
    // @Autowired annotation is used to inject the AirportRepository dependency into the AirportController class
    // private AirportRepository repo creates a new instance of the AirportRepository class ( AirportRepository repo = new AirportRepository() )
    // this is similar for the CityRepository and AircraftRepository classes
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

    // @GetMapping annotation is used to handle the HTTP GET requests matched with the given URI expression
    // @GetMapping("/airport/{id}") will get the airport with the id that is passed in the URI
    @GetMapping("/airport/{id}")
    public Optional<Airport> getAirportById(@PathVariable Long id) {
        return repo.findById(id);
    }

    // @GetMapping("/airport/passengers_search") will get the airport with the passenger's last name if it matches the last name that is passed in the URI
    @GetMapping("/airport/passengers_search")
    public List<Airport> getAirportByPassengers(@RequestParam String lastName) {
        return (List<Airport>) repo.findByPassengers_lastName(lastName);
    }

    // @GetMapping("/airport_passengers") the purpose of this is to answer the question: "What airports have passengers?"
    @GetMapping("/airports_passengers")
    private List<JSONObject> getAllAirportsByPassengers() {
        List <Airport> airportList = (List<Airport>) repo.findAll();
        List<JSONObject> arrayList = new ArrayList<>();
        airportList.forEach(i -> {
            // Only add airport records that have passenger data associated with them
            if (!i.getPassengers().isEmpty()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", i.getId());
                jsonObject.put("airportName", i.getAirportName());
                jsonObject.put("airportCode", i.getAirportCode());
                jsonObject.put("passengers", i.getPassengers());

                arrayList.add(jsonObject);
            }
        });

        return arrayList;
    }

    // @PostMapping("/airport") will create a new airport with the data that is passed in the request body
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

    //  @PutMapping("/airport/{id}") will update the airport with the id that is passed in the URI with the data that is passed in the request body
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

    // @DeleteMapping("/airport/{id}") will delete the airport with the id that is passed in the URI
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