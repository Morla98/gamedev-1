package com.unihannover.gamedev.repositories;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.unihannover.gamedev.models.Collector;

/**
 * A class that represents a table of the Database.
 * Contains every Colllector in the System.
 */
@RepositoryRestResource(collectionResourceRel = "collectors", path = "collectors")
public interface CollectorRepository extends PagingAndSortingRepository<Collector, Long> {

    /**
     * Find all collectors
     *
     * @return A List containing all Collectors
     */
    List<Collector> findAll();

    /**
     * Find a collector by its id
     *
     * @return A List containing all Collectors with given id
     */
    List<Collector> findById(@Param("id") String id);

    /**
     * Find a collector by its name
     *
     * @return A List containing all Collectors with given name
     */
    List<Collector> findByName(@Param("name") String name);
}
