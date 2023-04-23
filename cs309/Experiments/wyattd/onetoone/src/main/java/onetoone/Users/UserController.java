package onetoone.Users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import onetoone.Trips.Trip;
import onetoone.Trips.TripRepository;

/**
 * 
 * @author Vivek Bengre
 * 
 */ 

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TripRepository tripRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(path = "/users/{id}")
    User getUserById( @PathVariable String id){
        return userRepository.findBy(id);
    }

    @PostMapping(path = "/users")
    String createUser(@RequestBody User user){
        if (user == null)
            return failure;
        userRepository.save(user);
        return success;
    }

    @PutMapping("/users/{id}")
    User updateUser(@PathVariable String id, @RequestBody User request){
        User user = userRepository.findBy(id);
        if(user == null)
            return null;
        userRepository.save(request);
        return userRepository.findBy(id);
    }   
    
    @PutMapping("/users/{userId}/trips/{laptopId}")
    String assignTripToUser(@PathVariable String userId,@PathVariable int tripID){
        User user = userRepository.findBy(userId);
        Trip trip = tripRepository.findById(tripID);
        if(user == null || trip == null)
            return failure;
        trip.setUser(user);
        user.setLaptop(trip);
        userRepository.save(user);
        return success;
    }

    @DeleteMapping(path = "/users/{id}")
    String deleteUser(@PathVariable String id){
        userRepository.deleteById(id);
        return success;
    }
}
