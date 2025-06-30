package com.odorok.OdorokApplication.course.service;

public interface VisitedCourseQueryService {
    boolean checkVisitedCourse(Long userId, Long courseId);
}
