package com.odorok.OdorokApplication.course.service;

import com.odorok.OdorokApplication.course.dto.request.CourseScheduleRequest;

import java.time.LocalDateTime;

public interface CourseScheduleManageService {
    void registSchedule(CourseScheduleRequest request, Long userId);
    Long registCourseSchedule(Long courseId, LocalDateTime date, Long userId);
    boolean checkOverlappedSchedule(Long courseId, LocalDateTime date, Long userId);
}
