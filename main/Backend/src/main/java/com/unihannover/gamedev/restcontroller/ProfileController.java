package com.unihannover.gamedev.restcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.unihannover.gamedev.models.User;

@RestController
@RequestMapping("/profile")
public class CustomerController {

    @CrossOrigin(origins = "http://localhost:8082")
    @RequestMapping(value="/profile", method = RequestMethod.GET)
    public User getUsers() {
        User returnUser = new User();
        return returnUser;
    }

    @CrossOrigin(origins = "http://localhost:8082")
    @RequestMapping(value="/profile", method = RequestMethod.POST)
    public void updateProfile(@RequestBody String name, String mail, boolean hidden) {

        User user1 = new User();
        user1.setFirstName("Jhon");
        user1.setLastName("Doe");
        user1.setEmail("Jhon@Doe.com");
        user1.setUserName("someUserName");
        user1.setLevel(1);
        user1.setScore(1);
        user1.setAnonymous(true);

        System.out.println(user1.toString());

        user1.setAnonymous(hidden);
        user1.setUserName(name);
        user1.setEmail(mail);

        System.out.println(user1.toString());

    }


}
