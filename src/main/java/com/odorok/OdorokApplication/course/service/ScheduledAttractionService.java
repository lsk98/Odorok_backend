package com.odorok.OdorokApplication.course.service;

import com.odorok.OdorokApplication.domain.ScheduledAttraction;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduledAttractionService {
    List<ScheduledAttraction> queryScheduledAttractions(Long scheduledCourseId);
    void registAttractionSchedules(List<Long> ids, Long scheduledCourseId);
    void deleteAttractionSchedules(Long scheduledCourseId);
}
