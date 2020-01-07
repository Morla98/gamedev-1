package com.unihannover.gamedev.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unihannover.gamedev.models.User;
import com.unihannover.gamedev.repositories.UserRepository;

@RestController
public class UserController extends BaseController {

    @Autowired
    private UserRepository repository;

    @RequestMapping(value="/users/all", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @RequestMapping(value="/users/by-email", method = RequestMethod.GET)
    public List<User> getUsersByEmail(@RequestParam(value="email") String email) {
        return repository.findByEmail(email);
    }

    @RequestMapping(value="/users", method = RequestMethod.POST)
    public void updateProfile(@RequestBody User u) {

        System.out.println(u.toString());

    }
    
    @RequestMapping(value="/users", method = RequestMethod.PUT)
    public void addUser(@RequestBody User u) {
    	repository.save(u);
    }


}

