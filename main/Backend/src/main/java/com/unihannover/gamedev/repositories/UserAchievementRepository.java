package com.unihannover.gamedev.repositories;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.unihannover.gamedev.models.UserAchievement;

@RepositoryRestResource(collectionResourceRel = "user-achievements", path = "user-achievements")
public interface UserAchievementRepository extends PagingAndSortingRepository<UserAchievement, Long> {

    List<UserAchievement> findAll();
    List<UserAchievement> findByUserEmail(@Param("userEmail") String userEmail);
}
