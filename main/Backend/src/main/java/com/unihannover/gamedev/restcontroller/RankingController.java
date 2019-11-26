package com.unihannover.gamedev.restcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.unihannover.gamedev.models.User;
import java.util.List;

@RestController
@RequestMapping(/profile)
public class CustomerController {
    @RestController
    public class CustomerController {

        @CrossOrigin(origins = "http://localhost:8082")
        @RequestMapping(value="/ranking", method = RequestMethod.GET)
        public ArrayList<User> getUserListAnonymous() {

            ArrayList<User> users = new ArrayList<>();

            User user1 = new User();
            user1.firstName = "Jhon";
            user1.lastName = "Doe";
            user1.email = "Jhon@Doe.de";
            user1.userName = "myUserName";
            user1.level = 1;
            user1.score = 0;
            user1.anonymous = true;

            if(user1.anonymous) {
                users.add(user1);
            }

            System.out.println(users.get(0).firstName);

            return users;
        }
    }

}