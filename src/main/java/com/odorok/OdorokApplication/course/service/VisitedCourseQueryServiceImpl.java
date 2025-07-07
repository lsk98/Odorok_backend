package com.odorok.OdorokApplication.course.service;

import com.odorok.OdorokApplication.diary.repository.VisitedCourseRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        return visitedCourseRepository.findAvgStarsByCourseId(courseId).intValue();
    }

    @Override
    public Long queryReviewCount(Long courseId) {
        return visitedCourseRepository.countReviewsOf(courseId);
    }
}
