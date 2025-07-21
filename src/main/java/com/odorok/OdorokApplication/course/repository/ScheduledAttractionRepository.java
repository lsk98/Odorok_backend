package com.odorok.OdorokApplication.course.repository;

import com.odorok.OdorokApplication.domain.ScheduledAttraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduledAttractionRepository extends JpaRepository<ScheduledAttraction, Long> {
    List<ScheduledAttraction> findByScourseId(Long scourseId);
    Long countByScourseId(Long scourseId);
    Long deleteByScourseId(Long scourseId);
}
