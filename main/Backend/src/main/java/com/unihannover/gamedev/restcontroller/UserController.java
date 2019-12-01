package com.unihannover.gamedev.restcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.unihannover.gamedev.models.User;
import java.util.List;

@RestController
public class UserController {

    @CrossOrigin(origins = "http://localhost:8082")
    @RequestMapping(value="/profile", method = RequestMethod.GET)
    public User getUser() {
        User returnUser = new User();
        return returnUser;
    }

    @RequestMapping(value="/ranking", method = RequestMethod.GET)
    public ArrayList<User> getUserList() {
        ArrayList<User> users = new ArrayList<>();

        User user1 = new User();
        user1.firstName = "Jhon";
        user1.lastName = "Doe";
        user1.email = "Jhon@Doe.de";
        user1.userName = "myUserName";
        user1.level = 1;
        user1.score = 0;
        user1.anonymous = false;

        if(!user1.anonymous) {
            users.add(user1);
        }

        System.out.println(users.get(0).firstName);

        return users;
    }

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
