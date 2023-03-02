package com.keyin.aircraft;

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
public class AircraftController {
    @Autowired
    private AircraftRepository repo;

    @GetMapping("/aircraft")
    public List<Aircraft> getAllAircraft() {
        return (List<Aircraft>) repo.findAll();
    }

    @GetMapping("/aircraft/{id}")
    public Optional<Aircraft> getAircraftById(@PathVariable Long id) {
        return repo.findById(id);
    }

    @GetMapping("/aircraft/airports_search")
    public List<Aircraft> getAircraftByAirports(@RequestParam String airportName) {
        return (List<Aircraft>) repo.findByAirports_airportName(airportName);
    }

    @GetMapping("/aircraft/passengers_search")
    public List<Aircraft> getAircraftByPassengers(@RequestParam String lastName) {
        return (List<Aircraft>) repo.findByPassengers_lastName(lastName);
    }

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

    @PostMapping("/aircraft")
    public void createAircraft(@RequestBody Aircraft aircraft) {
        repo.save(aircraft);
    }

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
