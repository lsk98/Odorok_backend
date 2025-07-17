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
import com.odorok.OdorokApplication.domain.VisitedCourse;
import com.odorok.OdorokApplication.infrastructures.domain.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
    public List<DiseaseAndCourses> queryCoursesForDiseasesOf(Long userId, RecommendationCriteria criteria, Pageable pageable) {
        return queryCoursesForDiseaseOfBrutal(userId, criteria, pageable);
    }

    public List<DiseaseAndCourses> queryCoursesForDiseasesOfView(Long userId, RecommendationCriteria criteria, Pageable pageable) {
        List<DiseaseAndCourses> result = new ArrayList<>();

        List<UserDisease> diseases = userDiseaseQueryService.queryUserDiseases(userId);
        for (UserDisease disease : diseases) {
            result.add(this.queryCoursesOfDisease(disease.getDiseaseId(), criteria, pageable));
        }

        return result;
    }

    public DiseaseAndCourses queryCoursesOfDisease(Long diseaseId, RecommendationCriteria criteria, Pageable pageable) {
        List<DiseaseCourseStat> stats = diseaseCourseStatQueryService.queryDiseaseCourseStatFor(diseaseId);
        if(stats.isEmpty()) return null;

        stats.sort(criteria.comparator());

        DiseaseAndCourses result = new DiseaseAndCourses();
        result.setDiseaseCode(diseaseId);

        List<RecommendedCourseSummary> courses = new ArrayList<>();
        for(int i = 0; i < Math.min(pageable.getPageSize(), stats.size()); i++) {
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


    public List<DiseaseAndCourses> queryCoursesForDiseaseOfBrutal(Long userId, RecommendationCriteria criteria, Pageable pageable) {
        List<DiseaseAndCourses> result = new ArrayList<>();
        // 유저와 같은 질병을 가진 사람들의 아이디를 모아본다.
        // 사용자의 질병 코드를 읽어옴
        Set<Long> userDisease = new HashSet<>(
                userDiseaseQueryService.queryUserDiseases(userId)
                        .stream()
                        .map(UserDisease::getDiseaseId).toList());

        // 사용자와 같은 질병 코드를 가진 userID를 검색함.
        for(Long diseaseId : userDisease) { // 질병 하나당 한 번씩 수행함.
            Set<Long> sameDiseaseUsers = new HashSet<>();

            sameDiseaseUsers.addAll(userDiseaseQueryService.queryUsersHavingDisease(diseaseId)
                    .stream().map(UserDisease::getUserId).toList());

            // 현재 질병 id를 가진 사람들의 방문 코스 정보 모으기
            List<VisitedCourse> visitedCourses = new ArrayList<>();
            for(Long sameDiseaseUserId : sameDiseaseUsers) {
                visitedCourses.addAll(visitedCourseQueryService.queryVisitedCourses(sameDiseaseUserId));
            }

            // 통계정보를 저장해야 하니까..
            Map<Long, List<VisitedCourse>> mapForCourses = new HashMap<>();
            for(VisitedCourse course : visitedCourses) {
                Long courseId = course.getCourseId();
                if(!mapForCourses.containsKey(courseId)) {
                    mapForCourses.put(courseId, new ArrayList<>());
                }
                mapForCourses.get(courseId).add(course);
            }

            // 이제 코스 번호별로 통계 정보를 만들어야 함.
            List<CourseTempStat> courseRanking = new ArrayList<>();
            for(Long courseId : mapForCourses.keySet()) {
                List<VisitedCourse> list = mapForCourses.get(courseId);
                Double avg = list.stream().collect(Collectors.averagingDouble(VisitedCourse::getStars));
                Integer reviewCount = (int)list.stream().filter(r -> r.getReview() != null && !r.getReview().equals("")).count();
                CourseTempStat stat = new CourseTempStat(courseId, avg, reviewCount, (long)list.size());
                courseRanking.add(stat);
            }

            courseRanking.sort((i, j) -> j.avgStars().compareTo(i.avgStars()));


            DiseaseAndCourses summary = new DiseaseAndCourses();
            summary.setDiseaseCode(diseaseId);
            summary.setCourses(new ArrayList<RecommendedCourseSummary>());
            for(int i = 0; i < Math.min(pageable.getPageSize(), courseRanking.size()); i++) {
                Course course = courseRepository.findById(courseRanking.get(i).courseId())
                        .orElseThrow(() -> new IllegalArgumentException("지병 별 코스 추천(자바로 처리) : 해당 코스가 없습니다."));
                RecommendedCourseSummary courseSummary =new RecommendedCourseSummary(
                        course,
                        (int)Math.round(courseRanking.get(i).avgStars()), courseRanking.get(i).reviewCount, courseRanking.get(i).visitationCount
                );
                courseSummary.setGilName(routeQueryService.queryRouteNameByRouteIdx(course.getRouteIdx()));
                courseSummary.setVisited(visitedCourseQueryService.checkVisitedCourse(userId, course.getId()));
                summary.getCourses().add(courseSummary);
            }
            result.add(summary);
        }
        return result;
    }
    record CourseTempStat(Long courseId, Double avgStars, Integer reviewCount, Long visitationCount) {};
}
