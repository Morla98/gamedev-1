package com.unihannover.gamedev.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unihannover.gamedev.models.User;
import com.unihannover.gamedev.repositories.UserRepository;
import com.unihannover.gamedev.security.UserPrincipal;

/**
 * A controller to handle HTTP requests about users.
 */
@RestController
public class UserController extends BaseController {

    @Autowired
    private UserRepository repository;

    /**
     * Returns a list of all users in the system.
     *
     * @return The list with all users
     */
    @RequestMapping(value = "/users/all", method = RequestMethod.GET)
    public List<User> getAllUsers() {
       if (((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()) != null &&
    		   ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername() != null
    		   ) {
        	return repository.findByAnonymousFalse();
        }
        else return repository.findAll();
    }

    /**
     * Returns a list of users with a given E-Mail.
     *
     * @param email The primary key to identify users
     * @return A list of all users wth the given id
     */
    @RequestMapping(value = "/users/by-email", method = RequestMethod.GET)
    public User getUsersByEmail(@RequestParam(value = "email") String email) {
        return repository.findByEmail(email);
    }

    /**
     * Adds a user to the repository. If there is already a user with the given E-Mail in the repository,
     * the user information will be updated.
     *
     * @param u The user to update in the repository
     */
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public void updateProfile(@RequestBody User u) {

        System.out.println(u.toString());
    }

    @RequestMapping(value = "/users/profile", method = RequestMethod.GET)
    public User getProfile() {
        String userMail = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return repository.findByEmail(userMail);
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public void addUser(@RequestBody User u) {
        String userMail = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        if (u.getEmail().equals(userMail)) {
            repository.save(u);
        }
    }
}

