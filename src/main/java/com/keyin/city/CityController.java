package com.keyin.city;

// CityController.java is a REST controller class that handles the HTTP requests and responses.

import com.fasterxml.jackson.core.JsonProcessingException;
import net.minidev.json.JSONObject;
// net.minidev.json.JSONObject is used to import the JSONObject class from the json-smart library
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// the imports below are used to import the classes and interfaces that are used in the class AirportController
// without the javax.servlet.http.HttpServletResponse import, the HttpServletResponse response parameter in the updateAirport method will not work
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// @RestController annotation is used to create RESTful web services using Spring MVC.
// @CrossOrigin annotation is used to allow cross-origin requests.
// Cross-origin requests are requests that are made from a different domain than the domain of the resource being requested.
@RestController
@CrossOrigin
public class CityController {
    // @Autowired annotation is used to inject the CityRepository dependency into the CityController class.
    // private CityRepository repo creates a new instance of the CityRepository class ( CityRepository repo = new CityRepository() ).
    @Autowired
    private CityRepository repo;

    // @GetMapping annotation is used to handle the HTTP GET requests matched with the given URI expression.
    // @GetMapping("/cities") will get all the cities.
    @GetMapping("/cities")
    public List<City> getAllCities() {
        return (List<City>) repo.findAll();
    }
    // @GetMapping("/city/{id}") will get the city with the id that is passed in the URI.
    @GetMapping("/city/{id}")
    public Optional<City> getCityById(@PathVariable Long id) {
        return repo.findById(id);
    }
    //  @GetMapping("/cities/airports_search") will get the cities that have the airport name that is passed in the URI.
    @GetMapping("/cities/airports_search")
    public List<City> getCitiesByAirports(@RequestParam String airportName) {
        return (List<City>) repo.findByAirports_airportName(airportName);
    }
    // @GetMapping("/cities_airports") the purpose of this is to answer the question: "How do I get the cities that have airports?"
    @GetMapping("/cities_airports")
    private List<JSONObject> getAllCitiesByAirports() {
        List <City> cityList = (List<City>) repo.findAll();
        List<JSONObject> arrayList = new ArrayList<>();
        cityList.forEach(i -> {
            // Only add city records that have airport data associated with them.
            if (!i.getAirports().isEmpty()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", i.getId());
                jsonObject.put("cityName", i.getCityName());
                jsonObject.put("cityState", i.getCityState());
                jsonObject.put("airports", i.getAirports());

                arrayList.add(jsonObject);
            }
        });

        return arrayList;
    }
    // @PostMapping("/city") will create a new city with the data that is passed in.
    @PostMapping("/city")
    public void createCity(@RequestBody City city) {
        repo.save(city);
    }
    // @PutMapping("/city/{id}") will update the city with the id that is passed in the URI with the data that is passed in.
    @PutMapping("/city/{id}")
    public void updateCity(@PathVariable String id, @RequestBody City city, HttpServletResponse response) {
        Optional<City> returnValue = repo.findById(Long.parseLong(id));
        City cityToUpdate;

        if (returnValue.isPresent()) {
            cityToUpdate = returnValue.get();

            cityToUpdate.setCityName(city.getCityName());
            cityToUpdate.setCityState(city.getCityState());
            cityToUpdate.setCityPopulation(city.getCityPopulation());

            repo.save(cityToUpdate);
        } else {
            try {
                response.sendError(404, "City with id: " + city.getId() + " not found.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    // @DeleteMapping("/city/{id}") will delete the city with the id that is passed in the URI.
    @DeleteMapping("/city/{id}")
    public void deleteCity(@PathVariable String id, HttpServletResponse response) {
        Optional<City> returnValue = repo.findById(Long.parseLong(id));

        if (returnValue.isPresent()) {
            repo.deleteById(Long.parseLong(id));
        } else {
            try {
                response.sendError(404, "City with id: " + id + " not found.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
