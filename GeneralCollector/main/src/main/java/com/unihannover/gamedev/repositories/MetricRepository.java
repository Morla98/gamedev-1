package com.unihannover.gamedev.repositories;

import com.unihannover.gamedev.models.Metric;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetricRepository extends JpaRepository<Metric, Long> {
}
