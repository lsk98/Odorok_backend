package com.odorok.OdorokApplication.course.repository;

import com.odorok.OdorokApplication.infrastructures.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
