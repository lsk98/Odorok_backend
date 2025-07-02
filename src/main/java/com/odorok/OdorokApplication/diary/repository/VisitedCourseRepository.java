package com.odorok.OdorokApplication.course.repository;

import com.odorok.OdorokApplication.domain.VisitedCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitedCourseRepository extends JpaRepository<VisitedCourse, Long>, VisitedCourseRepositoryCustom {
    boolean existsByUserIdAndCourseId(Long userId, Long courseId);
}
