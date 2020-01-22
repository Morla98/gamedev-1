package com.unihannover.gamedev.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unihannover.gamedev.models.User;
import com.unihannover.gamedev.repositories.UserRepository;

/**
 * Represents A class that contains details of Users, used in transactions.
 *
 * @author Dominik Andrae
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

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
    public UserDetails loadUserByUsername(String email){
        // Let people login with email
        User user = userRepository.findByEmail((email));
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
        User user = userRepository.findByUid(id);
        return UserPrincipal.create(user);
    }
}