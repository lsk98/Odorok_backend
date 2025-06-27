package com.odorok.OdorokApplication.repository;

import com.odorok.OdorokApplication.domain.VisitedCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitedCourseRepository extends JpaRepository<VisitedCourse, Long>, VisitedCourseRepositoryCustom  {
}
