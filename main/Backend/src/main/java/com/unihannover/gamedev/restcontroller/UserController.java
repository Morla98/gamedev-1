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

@RestController
public class UserController extends BaseController {

    @Autowired
    private UserRepository repository;

    @RequestMapping(value="/users/all", method = RequestMethod.GET)
    public List<User> getAllUsers() {
    	
        return repository.findByAnonymousFalse();
    }

    @RequestMapping(value="/users/by-email", method = RequestMethod.GET)
    public User getUsersByEmail(@RequestParam(value="email") String email) {
        return repository.findByEmail(email);
    }

    @RequestMapping(value="/users/profile", method = RequestMethod.GET)
    public User getProfile() {
    	String userMail = ((UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    	return repository.findByEmail(userMail);
    }
    
    @RequestMapping(value="/users", method = RequestMethod.PUT)
    public void addUser(@RequestBody User u) {
    	String userMail = ((UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    	if (u.getEmail().equals(userMail)) {
    		repository.save(u);
    	}
    }


}

