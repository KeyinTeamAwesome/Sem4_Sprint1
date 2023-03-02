package com.keyin.city;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class CityController {
    @Autowired
    private CityRepository repo;

    @GetMapping("/cities")
    public List<City> getAllCities() {
        return (List<City>) repo.findAll();
    }

    @GetMapping("/city/{id}")
    public Optional<City> getCityById(@PathVariable Long id) {
        return repo.findById(id);
    }

    @GetMapping("/cities/airports_search")
    public List<City> getCitiesByAirports(@RequestParam String airportName) {
        return (List<City>) repo.findByAirports_airportName(airportName);
    }

    @GetMapping("/cities_airports")
    private List<JSONObject> getAllCitiesByAirports() {
        List <City> cityList = (List<City>) repo.findAll();
        List<JSONObject> arrayList = new ArrayList<>();
        cityList.forEach(i -> {
            // Only add city records that have airport data associated with them
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

    @PostMapping("/city")
    public void createCity(@RequestBody City city) {
        repo.save(city);
    }

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
