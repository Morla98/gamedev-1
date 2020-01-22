package com.unihannover.gamedev.repositories;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.unihannover.gamedev.models.User;

/**
 * A class that represents a table of the Database.
 * Contains every User in the System.
 */
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    /**
     * Find all Users.
     *
     * @return A List containing all Users
     */
    List<User> findAll();

    /**
     * Find all Users that are anonymous.
     *
     * @return A List containing all anonymous Users
     */
    List<User> findByAnonymousFalse();

    /**
     * Find all Users by email adress.
     *
     * @return A List containing all Users with given email
     */
    User findByEmail(@Param("email") String email);

    /**
     * Find all Users by id.
     *
     * @return A List containing all Users with given id
     */
    User findByUid(@Param("uid") String uid);
}
