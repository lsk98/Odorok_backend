package com.odorok.OdorokApplication.course.repository;

import com.odorok.OdorokApplication.infrastructures.domain.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Page<Course> findBySidoCodeAndSigunguCode(Integer sidoCode, Integer sigunguCode, Pageable pageable);
}
