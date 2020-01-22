package com.unihannover.gamedev.repositories;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.unihannover.gamedev.models.UserAchievement;

/**
 * A class that represents a table of the Database.
 * Contains every UserAchievement (Relation between User and Achievment) in the System.
 */
@RepositoryRestResource(collectionResourceRel = "user-achievements", path = "user-achievements")
public interface UserAchievementRepository extends PagingAndSortingRepository<UserAchievement, Long> {

    /**
     * Find all UserAchievements.
     *
     * @return A List containing all UserAchievements
     */
    List<UserAchievement> findAll();

    /**
     * Find all UserAchievements.
     *
     * @return A List containing all UserAchievements for a given user
     */
    List<UserAchievement> findByUserEmail(@Param("userEmail") String userEmail);
}
