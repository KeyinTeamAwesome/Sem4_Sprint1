package com.keyin.aircraft;

// AircraftController.java is a controller class and is used to handle the HTTP requests and responses

import com.fasterxml.jackson.core.JsonProcessingException;
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
public class AircraftController {
    // @Autowired annotation is used to inject the AircraftRepository dependency into the AircraftController class
    // private AircraftRepository repo creates a new instance of the AircraftRepository class ( AircraftRepository repo = new AircraftRepository() )
    @Autowired
    private AircraftRepository repo;
    // @GetMapping annotation is used to handle the HTTP GET requests matched with the given URI expression
    // @GetMapping("/aircraft") will get all the aircraft
    @GetMapping("/aircraft")
    public List<Aircraft> getAllAircraft() {
        return (List<Aircraft>) repo.findAll();
    }
    // @GetMapping("/aircraft/{id}") will get the aircraft with the id that is passed in the URI
    @GetMapping("/aircraft/{id}")
    public Optional<Aircraft> getAircraftById(@PathVariable Long id) {
        return repo.findById(id);
    }
    // @GetMapping("/aircraft/airline_search") will get the aircraft with the airline name if it matches the airline name that is passed in the URI
    @GetMapping("/aircraft/airports_search")
    public List<Aircraft> getAircraftByAirports(@RequestParam String airportName) {
        return (List<Aircraft>) repo.findByAirports_airportName(airportName);
    }
    // @GetMapping("/aircraft/passengers_search") will get the aircraft with the passenger last name if it matches the passenger last name that is passed in the URI
    @GetMapping("/aircraft/passengers_search")
    public List<Aircraft> getAircraftByPassengers(@RequestParam String lastName) {
        return (List<Aircraft>) repo.findByPassengers_lastName(lastName);
    }
    //  @GetMapping("/aircraft_passengers") the purpose of this is to answer the question: "What aircraft have how many passengers?"
    @GetMapping("/aircraft_passengers")
    private List<JSONObject> getAllAircraftByPassengers() {
        List <Aircraft> aircraftList = (List<Aircraft>) repo.findAll();
        List<JSONObject> arrayList = new ArrayList<>();
        aircraftList.forEach(i -> {
            // Only add aircraft records that have passenger data associated with them
            if (!i.getPassengers().isEmpty()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", i.getId());
                jsonObject.put("airlineName", i.getAirlineName());
                jsonObject.put("type", i.getType());
                jsonObject.put("numberOfPassengers", i.getNumberOfPassengers());
                jsonObject.put("passengers", i.getPassengers());


                arrayList.add(jsonObject);
            }
        });

        return arrayList;
    }
    //  @GetMapping("/aircraft_airports") the purpose of this is to answer the question: "What aircraft have been to what airports?"
    @GetMapping("/aircraft_airports")
    private List<JSONObject> getAllAircraftByAirports() {
        List <Aircraft> aircraftList = (List<Aircraft>) repo.findAll();
        List<JSONObject> arrayList = new ArrayList<>();
        aircraftList.forEach(i -> {
            // Only add aircraft records that have airport data associated with them
            if (!i.getAirports().isEmpty()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", i.getId());
                jsonObject.put("airlineName", i.getAirlineName());
                jsonObject.put("type", i.getType());
                jsonObject.put("numberOfPassengers", i.getNumberOfPassengers());
                jsonObject.put("airports", i.getAirports());

                arrayList.add(jsonObject);
            }
        });

        return arrayList;
    }
    //  @PostMapping("/aircraft") will create a new aircraft with the data that is passed in the request body
    @PostMapping("/aircraft")
    public void createAircraft(@RequestBody Aircraft aircraft) {
        repo.save(aircraft);
    }
    // @PutMapping("/aircraft/{id}") will update the aircraft with the id that is passed in the URI with the data that is passed in the request body
    @PutMapping("/aircraft/{id}")
    public void updateAircraft(@PathVariable String id, @RequestBody Aircraft aircraft, HttpServletResponse response) {
        Optional<Aircraft> returnValue = repo.findById(Long.parseLong(id));
        Aircraft aircraftToUpdate;

        if (returnValue.isPresent()) {
            aircraftToUpdate = returnValue.get();

            aircraftToUpdate.setType(aircraft.getType());
            aircraftToUpdate.setAirlineName(aircraft.getAirlineName());
            aircraftToUpdate.setNumberOfPassengers(aircraft.getNumberOfPassengers());

            repo.save(aircraftToUpdate);
        } else {
            try {
                response.sendError(404, "Aircraft with id: " + aircraft.getId() + " not found.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    // @DeleteMapping("/aircraft/{id}") will delete the aircraft with the id that is passed in the URI
    @DeleteMapping("/aircraft/{id}")
    public void deleteAircraft(@PathVariable String id, HttpServletResponse response) {
        Optional<Aircraft> returnValue = repo.findById(Long.parseLong(id));

        if (returnValue.isPresent()) {
            repo.deleteById(Long.parseLong(id));
        } else {
            try {
                response.sendError(404, "Aircraft with id: " + id + " not found.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
