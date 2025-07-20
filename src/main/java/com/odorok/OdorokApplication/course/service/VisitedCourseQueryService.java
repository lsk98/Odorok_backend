package com.odorok.OdorokApplication.course.service;

import com.odorok.OdorokApplication.course.dto.process.CourseStat;
import com.odorok.OdorokApplication.domain.VisitedCourse;

import java.util.List;

public interface VisitedCourseQueryService {
    boolean checkVisitedCourse(Long userId, Long courseId);
    Integer queryAverageStars(Long courseId);
    Long queryReviewCount(Long courseId);
    List<CourseStat> queryCourseStatistics();
    List<VisitedCourse> queryVisitedCourses(Long userId);
}
