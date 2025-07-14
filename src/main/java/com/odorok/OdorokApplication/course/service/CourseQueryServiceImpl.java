package com.odorok.OdorokApplication.course.service;

import com.odorok.OdorokApplication.course.domain.DiseaseCourseStat;
import com.odorok.OdorokApplication.course.dto.process.CourseStat;
import com.odorok.OdorokApplication.course.dto.response.item.CourseDetail;
import com.odorok.OdorokApplication.course.dto.response.item.CourseSummary;
import com.odorok.OdorokApplication.course.dto.response.item.DiseaseAndCourses;
import com.odorok.OdorokApplication.course.dto.response.item.RecommendedCourseSummary;
import com.odorok.OdorokApplication.course.repository.CourseRepository;
import com.odorok.OdorokApplication.course.repository.UserDiseaseRepository;
import com.odorok.OdorokApplication.domain.UserDisease;
import com.odorok.OdorokApplication.infrastructures.domain.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseQueryServiceImpl implements CourseQueryService{
    private final CourseRepository courseRepository;
    private final RouteQueryService routeQueryService;
    private final VisitedCourseQueryService visitedCourseQueryService;
    private final PathCoordQueryService pathCoordQueryService;
    private final DiseaseCourseStatQueryService diseaseCourseStatQueryService;
    private final UserDiseaseQueryService userDiseaseQueryService;

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
            summary.setGilName(routeQueryService.queryRouteNameByRouteIdx(course.getRouteIdx()));
            if(userId != null) summary.setVisited(visitedCourseQueryService.checkVisitedCourse(userId, course.getId()));
            return summary;
        }).toList();
    }

    @Override
    public CourseDetail queryCourseDetail(Long courseId) {
        CourseDetail detail = new CourseDetail(courseRepository.findById(courseId).orElseThrow(
                ()->new IllegalArgumentException("존재하지 않는 '코스' 식별자 : " + courseId)));
        detail.setAvgStars(visitedCourseQueryService.queryAverageStars(courseId));
        detail.setReviewCount(visitedCourseQueryService.queryReviewCount(courseId));
        detail.setCoords(pathCoordQueryService.queryCoursePathCoords(courseId));
        return detail;
    }

    @Override
    public List<RecommendedCourseSummary> queryTopRatedCourses(RecommendationCriteria criteria) {
        // 별점 내림차순으로 코스를 정렬한다.
        List<CourseStat> stats = new ArrayList<>(visitedCourseQueryService.queryCourseStatistics());
        stats.sort(criteria.comparator());

        // 상위 5개 코스 정보를 요약한다.
        List<RecommendedCourseSummary> result = new ArrayList<>();
        for(int i = 0; i < Integer.min(5, stats.size()); i++) {
            result.add(new RecommendedCourseSummary(courseRepository.findById(stats.get(i).getCourseId()).orElseThrow(
                    () -> new IllegalArgumentException("top stars 에러. 해당 코스 아이디가 존재하지 않습니다.")
            ), (int)Math.round(stats.get(i).getAvgStars()), stats.get(i).getReviewCount().intValue(), stats.get(i).getVisitationCount()));
        }

        return result;
    }

    @Override
    public List<DiseaseAndCourses> queryCoursesForDiseasesOf(Long userId, RecommendationCriteria criteria) {
        List<DiseaseAndCourses> result = new ArrayList<>();

        List<UserDisease> diseases = userDiseaseQueryService.queryUserDiseases(userId);
        for(UserDisease disease : diseases) {
            result.add(this.queryCoursesForDisease(disease.getDiseaseId(), criteria));
        }

        return result;
    }

    @Override
    public DiseaseAndCourses queryCoursesForDisease(Long diseaseId, RecommendationCriteria criteria) {
        List<DiseaseCourseStat> stats = diseaseCourseStatQueryService.queryDiseaseCourseStatFor(diseaseId);
        if(stats.isEmpty()) return null;

        stats.sort(criteria.comparator());

        DiseaseAndCourses result = new DiseaseAndCourses();
        result.setDiseaseCode(diseaseId);

        List<RecommendedCourseSummary> courses = new ArrayList<>();
        for(int i = 0; i < Math.min(5, stats.size()); i++) {
            courses.add(new RecommendedCourseSummary(
                    courseRepository.findById(stats.get(i).getCourseId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 코스 아이디로 조회를 시도했습니다.")),
                    (int)Math.round(stats.get(i).getAvgStars()),
                    stats.get(i).getReviewCount().intValue(),
                    stats.get(i).getVisitationCount()
                    )
            );
        }

        result.setCourses(courses);
        return result;
    }

}
