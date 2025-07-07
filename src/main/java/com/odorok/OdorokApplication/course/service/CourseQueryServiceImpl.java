package com.odorok.OdorokApplication.course.service;

import com.odorok.OdorokApplication.course.dto.response.item.CourseDetail;
import com.odorok.OdorokApplication.course.dto.response.item.CourseSummary;
import com.odorok.OdorokApplication.course.repository.CourseRepository;
import com.odorok.OdorokApplication.infrastructures.domain.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseQueryServiceImpl implements CourseQueryService{
    private final CourseRepository courseRepository;
    private final RouteQueryService routeQueryService;
    private final VisitedCourseQueryService visitedCourseQueryService;
    private final PathCoordQueryService pathCoordQueryService;


    @Override
    public List<CourseSummary> queryCoursesByRegion(Integer sidoCode, Integer sigunguCode, Long userId, Pageable pageable) {
        Page<Course> page = courseRepository.findBySidoCodeAndSigunguCode(sidoCode, sigunguCode, pageable);
        return summarizeCourseCollection(userId, page.getContent());
    }

    @Override
    public List<CourseSummary> queryAllCourses(Long userId, Pageable pageable) {
        Page<Course> page = courseRepository.findAll(pageable);
        return summarizeCourseCollection(userId, page.getContent());
    }

    @Override
    public List<CourseSummary> summarizeCourseCollection(Long userId, List<Course> courses) {
        return courses.stream().map(course -> {
            CourseSummary summary = new CourseSummary(course); // 방문 했는지를 표시해야하는데, userId가 null인 경우에는 이 작업 스킵.
            if(userId != null) summary.setVisited(visitedCourseQueryService.checkVisitedCourse(userId, course.getId()));
            return summary;
        }).toList();
    }

    @Override
    public CourseDetail queryCourseDetail(Long courseId) {
        CourseDetail detail = new CourseDetail(courseRepository.findById(courseId).orElseThrow(
                ()->new IllegalArgumentException("존재하지 않는 '명소' 식별자 : " + courseId)));
        detail.setAvgStars(visitedCourseQueryService.queryAverageStars(courseId));
        detail.setReviewCount(visitedCourseQueryService.queryReviewCount(courseId));
        detail.setCoords(pathCoordQueryService.queryCoursePathCoords(courseId));
        return detail;
    }

}
