package com.odorok.OdorokApplication.course.service;

import com.odorok.OdorokApplication.course.dto.response.item.VisitationScheduleSummary;
import com.odorok.OdorokApplication.course.repository.CourseRepository;
import com.odorok.OdorokApplication.course.repository.ScheduledAttractionRepository;
import com.odorok.OdorokApplication.course.repository.ScheduledCourseRepository;
import com.odorok.OdorokApplication.domain.ScheduledCourse;
import com.odorok.OdorokApplication.infrastructures.domain.Course;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseScheduleQueryServiceImpl implements CourseScheduleQueryService{
    private final ScheduledCourseRepository scheduledCourseRepository;
    private final RouteQueryService routeQueryService;
    private final CourseRepository courseRepository;
    private final ScheduledAttractionRepository scheduledAttractionRepository;

    @Override
    public List<VisitationScheduleSummary> queryAllSchedule(Long userId) {
        List<ScheduledCourse> schedule = scheduledCourseRepository.findByUserId(userId);
        List<VisitationScheduleSummary> summaries = new ArrayList<>();

        for(ScheduledCourse item : schedule) {
            Course course = courseRepository.findById(item.getCourseId()).orElseThrow(()->new IllegalArgumentException("없는 코스 아이디 입니다. (id = " + item.getCourseId()+")"));
            summaries.add(VisitationScheduleSummary.builder()
                            .dueDate(item.getDueDate())
                            .gilName(routeQueryService.queryRouteNameByRouteIdx(course.getRouteIdx()))
                            .courseName(course.getName())
                            .attractionCount(scheduledAttractionRepository.countByScourseId(item.getId()))
                            .build());
        }
        return summaries;
    }
}
