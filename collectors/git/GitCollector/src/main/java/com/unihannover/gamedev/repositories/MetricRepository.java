package com.unihannover.gamedev.repositories;

import com.unihannover.gamedev.models.Metric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MetricRepository extends JpaRepository<Metric, Long> {

    List<Metric> findByUseremail(@Param("useremail") String useremail);
}
