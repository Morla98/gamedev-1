package com.unihannover.gamedev.repositories;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.unihannover.gamedev.models.Achievement;

@RepositoryRestResource(collectionResourceRel = "achievements", path = "achievements")
public interface AchievementRepository extends PagingAndSortingRepository<Achievement, Long> {

	List<Achievement> findAll();
	List<Achievement> findByName(@Param("name") String name);
	List<Achievement> findByCollectorId(@Param("collectorId") String id);
	Achievement findById(@Param("id") String id);
}
