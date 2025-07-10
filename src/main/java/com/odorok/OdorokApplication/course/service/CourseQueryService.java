package com.odorok.OdorokApplication.course.service;

import com.odorok.OdorokApplication.course.dto.process.CourseStat;
import com.odorok.OdorokApplication.course.dto.process.CourseStatSummary;
import com.odorok.OdorokApplication.course.dto.response.item.CourseDetail;
import com.odorok.OdorokApplication.course.dto.response.item.CourseSummary;
import com.odorok.OdorokApplication.course.dto.response.item.DiseaseAndCourses;
import com.odorok.OdorokApplication.course.dto.response.item.RecommendedCourseSummary;
import com.odorok.OdorokApplication.infrastructures.domain.Course;
import org.springframework.data.domain.Pageable;

import java.util.Comparator;
import java.util.List;

public interface CourseQueryService {
    List<CourseSummary> queryCoursesByRegion(Integer sidoCode, Integer sigunguCode, Long userId, Pageable pageable);
    List<CourseSummary> queryAllCourses(Long userId, Pageable pageable);
    List<CourseSummary> summarizeCourseCollection( Long userId, List<Course> courses);
    CourseDetail queryCourseDetail(Long courseId);
    List<RecommendedCourseSummary> queryTopRatedCourses(RecommendationCriteria criteria);
    List<DiseaseAndCourses> queryCoursesForDiseasesOf(Long userId, RecommendationCriteria criteria);
    DiseaseAndCourses queryCoursesForDisease(Long diseaseId, RecommendationCriteria criteria);

    enum RecommendationCriteria {
        STARS((a, b) -> b.getAvgStars().compareTo(a.getAvgStars())),
        REVIEWS((a, b) -> b.getAvgStars().compareTo(a.getAvgStars())),
        TOTAL_VISITATION((a, b) -> b.getVisitationCount().compareTo(a.getVisitationCount()));

        RecommendationCriteria(Comparator<CourseStatSummary> comp) {
            this.comparator = comp;
        }

        private Comparator<CourseStatSummary> comparator;

        public Comparator<CourseStatSummary> comparator() {
            return this.comparator;
        }
    }
}