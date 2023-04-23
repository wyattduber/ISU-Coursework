package onetoone.Trips;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Vivek Bengre
 * 
 */ 

@RestController
public class TripController {

    @Autowired
    TripRepository tripRepository;
    
    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/trips")
    List<Trip> getAllTrips(){
        return tripRepository.findAll();
    }

    @GetMapping(path = "/trips/{id}")
    Trip getTripById(@PathVariable int id){
        return tripRepository.findById(id);
    }

    @PostMapping(path = "/trips")
    String createTrip(@RequestBody Trip trip){
        if (trip == null)
            return failure;
        tripRepository.save(trip);
        return success;
    }

    @PutMapping(path = "/trips/{id}")
    Trip updateTrip(@PathVariable int id, @RequestBody Trip request){
        Trip trip = tripRepository.findById(id);
        if(trip == null)
            return null;
        tripRepository.save(request);
        return tripRepository.findById(id);
    }

    @DeleteMapping(path = "/trips/{id}")
    String deleteTrip(@PathVariable int id){
        tripRepository.deleteById(id);
        return success;
    }
}
