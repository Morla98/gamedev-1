package com.unihannover.gamedev.repositories;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.unihannover.gamedev.models.Collector;

@RepositoryRestResource(collectionResourceRel = "collectors", path = "collectors")
public interface CollectorRepository extends PagingAndSortingRepository<Collector, Long> {

    /**
     * Find all collectors
     */
    List<Collector> findAll();

    /**
     * Find a collector by its id
     */
    List<Collector> findById(@Param("id") String id);

    /**
     * Find a collector by its name
     */
    List<Collector> findByName(@Param("name") String name);
}
