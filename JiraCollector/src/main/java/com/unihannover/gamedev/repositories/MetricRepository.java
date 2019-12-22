package com.unihannover.gamedev.repositories;

import com.unihannover.gamedev.models.Metric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MetricRepository extends JpaRepository<Metric, Long> {

    @Query("SELECT DISTINCT m.issueKey From Metric m WHERE m.userEmail = :userEmail AND m.eventType = :eventType")
    List<String> getDistinctIssueKeysByUserAndEvent(@Param("userEmail") String userEmail, @Param("eventType") String eventType);

    @Query("SELECT DISTINCT m.issueKey From Metric m WHERE m.userEmail = :userEmail AND m.action = :action")
    List<String> getDistinctIssueKeysByUserAndAction(@Param("userEmail") String userEmail, @Param("action") String action);

    @Query("SELECT DISTINCT m.issueKey From Metric m WHERE m.userEmail = :userEmail AND m.action = :action AND m.issueType = :issueType")
    List<String> getDistinctIssueKeysByUserAndActionAndIssueType(@Param("userEmail") String userEmail, @Param("action") String action, @Param("issueType") String issueType);
}
