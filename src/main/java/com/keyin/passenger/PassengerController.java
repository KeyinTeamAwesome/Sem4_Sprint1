package com.keyin.passenger;

// PassengerController.java this is a controller class that is used to handle the http requests and responses

// some of the following imports are used to import the classes and interfaces that are used in the class PassengerController
import com.keyin.aircraft.Aircraft;
import com.keyin.aircraft.AircraftRepository;
import com.keyin.city.City;
import com.keyin.city.CityRepository;
import com.keyin.airport.Airport;
import com.keyin.airport.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// the imports below are used to import the classes and interfaces that are used in the class AirportController
// without the javax.servlet.http.HttpServletResponse import, the HttpServletResponse response parameter in the updateAirport method will not work
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

// @RestController annotation is used to create RESTful web services using Spring MVC
// @CrossOrigin annotation is used to allow cross-origin requests
// cross-origin requests are requests that are made from a different domain than the domain of the resource being requested
@RestController
@CrossOrigin
public class PassengerController {
    // @Autowired annotation is used to inject the PassengerRepository dependency into the PassengerController class
    // private PassengerRepository repo creates a new instance of the PassengerRepository class ( PassengerRepository repo = new PassengerRepository() )
    @Autowired
    private PassengerRepository repo;
    @Autowired
    private CityRepository cityRepo;
    @Autowired
    private AircraftRepository aircraftRepo;
    @Autowired
    private AirportRepository airportRepo;

    // @GetMapping annotation is used to handle the HTTP GET requests matched with the given URI expression
    // @GetMapping("/passengers") will get all the passengers
    @GetMapping("/passengers")
    public List<Passenger> getAllPassengers() {
        return (List<Passenger>) repo.findAll();
    }
    //  @GetMapping("/passenger/{id}") will get the passenger with the id that is passed in the URI
    @GetMapping("/passenger/{id}")
    public Optional<Passenger> getPassengerById(@PathVariable Long id) {
        return repo.findById(id);
    }
    //  @PostMapping("/passenger") will create a new passenger
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
    //  @PutMapping("/passenger/{id}") will update the passenger with the id that is passed in the URI
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
    //  @DeleteMapping("/passenger/{id}") will delete the passenger with the id that is passed in the URI
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
