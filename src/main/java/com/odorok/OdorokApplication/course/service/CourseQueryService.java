package com.odorok.OdorokApplication.course.service;

import com.odorok.OdorokApplication.course.dto.response.item.CourseSummary;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CourseQueryService {
    List<CourseSummary> findCoursesByRegion(Integer sidoCode, Integer sigunguCode, Long userId, Pageable pageable);
}