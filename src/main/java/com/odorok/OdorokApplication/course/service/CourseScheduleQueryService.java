package com.odorok.OdorokApplication.course.service;

import com.odorok.OdorokApplication.course.dto.response.item.VisitationScheduleSummary;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CourseScheduleQueryService {
    List<VisitationScheduleSummary> queryAllSchedule(Long userId);

}
