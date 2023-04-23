package coms309RoundTrip.Airplanned.controller;

import coms309RoundTrip.Airplanned.model.User;

import coms309RoundTrip.Airplanned.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("user/all")
    List<User> GetAllUser(){
        return userRepository.findAll();
    }

    @PostMapping("user/post/{u}/{p}")
    User PostUserByPath(@PathVariable String u, @PathVariable String p){
        User newUser = new User();
        newUser.setUsername(u);
        newUser.setPassword(p);
        userRepository.save(newUser);
        return newUser;
    }

    @PostMapping("user/post/{u}/{p}/{i}")
    User PostUserByPathWithID(@PathVariable String u, @PathVariable String p, @PathVariable int i){
        User newUser = new User();
        newUser.setId(i);
        newUser.setUsername(u);
        newUser.setPassword(p);
        userRepository.save(newUser);
        return newUser;
    }

    @PostMapping("user/post")
    User PostUserByPath(@RequestBody User newUser){
        userRepository.save(newUser);
        return newUser;
    }

}
