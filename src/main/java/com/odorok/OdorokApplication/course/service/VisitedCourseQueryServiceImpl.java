package com.odorok.OdorokApplication.course.service;

import com.odorok.OdorokApplication.course.dto.process.CourseStat;
import com.odorok.OdorokApplication.diary.repository.VisitedCourseRepository;
import com.odorok.OdorokApplication.domain.VisitedCourse;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitedCourseQueryServiceImpl implements VisitedCourseQueryService{
    private final VisitedCourseRepository visitedCourseRepository;

    @Override
    public boolean checkVisitedCourse(Long userId, Long courseId) {
        return visitedCourseRepository.existsByUserIdAndCourseId(userId, courseId);
    }

    @Override
    public Integer queryAverageStars(Long courseId) {
        Double res = visitedCourseRepository.findAvgStarsByCourseId(courseId);
        if(res != null) return res.intValue();
        else return 0;
    }

    @Override
    public Long queryReviewCount(Long courseId) {
        return visitedCourseRepository.countReviewsOf(courseId);
    }

    @Override
    public List<CourseStat> queryCourseStatistics() {
        return visitedCourseRepository.summarizeCourseFeedback();
    }

    @Override
    public List<VisitedCourse> queryVisitedCourses(Long userId) {
        return visitedCourseRepository.findByUserId(userId);
    }
}
