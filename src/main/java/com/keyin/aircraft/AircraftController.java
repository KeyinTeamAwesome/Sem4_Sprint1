package com.keyin.aircraft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class AircraftController {
    @Autowired
    private AircraftRepository repo;

    @GetMapping("/aircrafts")
    public List<Aircraft> getAllAircrafts() {
        return (List<Aircraft>) repo.findAll();
    }

    @GetMapping("/aircraft/{id}")
    public Optional<Aircraft> getAircraftById(@PathVariable Long id) {
        return repo.findById(id);
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
