package com.odorok.OdorokApplication.course.service;

import com.odorok.OdorokApplication.course.dto.response.item.CourseSummary;
import com.odorok.OdorokApplication.course.exception.RegionCodeException;
import com.odorok.OdorokApplication.course.repository.CourseRepository;
import com.odorok.OdorokApplication.infrastructures.domain.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseQueryServiceImpl implements CourseQueryService{
    private final CourseRepository courseRepository;
    private final RouteQueryService routeQueryService;
    private final VisitedCourseQueryService visitedCourseQueryService;

    @Override
    public List<CourseSummary> findCoursesByRegion(Integer sidoCode, Integer sigunguCode, Long userId, Pageable pageable) {
        Page<Course> page = courseRepository.findBySidoCodeAndSigunguCode(sidoCode, sigunguCode, pageable);

        List<CourseSummary> result = page.getContent().stream()
                .map(course -> {
                    CourseSummary summary = new CourseSummary(course);
                    summary.setGilName(routeQueryService.queryRouteNameByRouteIdx(course.getRouteIdx()));
                    if(userId != null) summary.setVisited(visitedCourseQueryService.checkVisitedCourse(userId, course.getId()));
                    return summary;
                }).toList();

        return result;
    }
}
