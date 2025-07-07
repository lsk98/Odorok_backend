package com.odorok.OdorokApplication.course.service;

import com.odorok.OdorokApplication.course.dto.response.item.CourseDetail;
import com.odorok.OdorokApplication.course.dto.response.item.CourseSummary;
import com.odorok.OdorokApplication.infrastructures.domain.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CourseQueryService {
    List<CourseSummary> queryCoursesByRegion(Integer sidoCode, Integer sigunguCode, Long userId, Pageable pageable);
    List<CourseSummary> queryAllCourses(Long userId, Pageable pageable);
    List<CourseSummary> summarizeCourseCollection( Long userId, List<Course> courses);
    CourseDetail queryCourseDetail(Long courseId);
}