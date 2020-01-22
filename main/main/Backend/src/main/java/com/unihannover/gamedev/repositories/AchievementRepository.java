package com.unihannover.gamedev.repositories;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.unihannover.gamedev.models.Achievement;


/**
 * A class that represents a table of the Database.
 * Contains every Achievement in the System.
 */
@RepositoryRestResource(collectionResourceRel = "achievements", path = "achievements")
public interface AchievementRepository extends PagingAndSortingRepository<Achievement, Long> {

	/**
	 * Find all Achievements.
	 *
	 * @return A List containing all Achievements
	 */
	List<Achievement> findAll();

	/**
	 * Find all Achievements by name.
	 *
	 * @return A List containing all Achievements, with given name
	 */
	List<Achievement> findByName(@Param("name") String name);

	/**
	 * Find all Achievements by collector.
	 *
	 * @return A List containing all Achievements with given collector
	 */
	List<Achievement> findByCollectorId(@Param("collectorId") String id);

	/**
	 * Find all Achievements by id.
	 *
	 * @return A List containing all Achievements with given id
	 */
	Achievement findById(@Param("id") String id);
}
