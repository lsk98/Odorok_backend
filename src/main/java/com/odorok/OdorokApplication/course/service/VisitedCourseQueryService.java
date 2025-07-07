package com.odorok.OdorokApplication.course.service;

public interface VisitedCourseQueryService {
    boolean checkVisitedCourse(Long userId, Long courseId);
    Integer queryAverageStars(Long courseId);
    Long queryReviewCount(Long courseId);
}
