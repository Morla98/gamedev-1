package com.unihannover.gamedev.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unihannover.gamedev.models.User;

/**
 * Represents A class that contains details of Users, used in transactions.
 *
 * @author Dominik Andrae
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    //@Autowired
    //UserRepository userRepository;

    /**
     * Returns the details of a User, given the Users name or E-Mail address (primary key).
     * Exception will not be thrown right now, since the code is commented.
     *
     * @param usernameOrEmail The id of the User
     * @return The details of the User
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {
        // Let people login with either username or email
        /*User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> 
                        new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail)
        );*/

    	// User dummy user for now
    	User user = new User();
    	user.setEmail("Test@GamerDads.com");
    	user.setUserName("Dave");
        return UserPrincipal.create(user);
    }

    /**
     * Returns the details of a User, given the User id.
     * Userid(uid) is a redundant attribute.
     * Mostly used in JWTAuthenticationFilter.
     *
     * @param id The user id
     * @return The details of the user
     */
    @Transactional
    public UserDetails loadUserById(String id) {
       /* User user = userRepository.findById(id).orElseThrow(
            () -> new UsernameNotFoundException("User not found with id : " + id)
        );*/

    	//User dummy user for now
        User user = new User();
    	user.setEmail("Test@GamerDads.com");
    	user.setUserName("Dave");
        return UserPrincipal.create(user);
    }
}