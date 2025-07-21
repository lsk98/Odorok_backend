package com.odorok.OdorokApplication.course.repository;

import com.odorok.OdorokApplication.domain.ScheduledCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduledCourseRepository extends JpaRepository<ScheduledCourse, Long> {

    List<ScheduledCourse> findByUserId(Long userId);
    boolean existsByCourseIdAndDueDateAndUserId(Long courseId, LocalDateTime dueDate, Long userId);
}
