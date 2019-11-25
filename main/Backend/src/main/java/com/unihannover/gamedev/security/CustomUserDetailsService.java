package com.unihannover.gamedev.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unihannover.gamedev.models.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    //@Autowired
    //UserRepository userRepository;

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

    // This method is used by JWTAuthenticationFilter
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