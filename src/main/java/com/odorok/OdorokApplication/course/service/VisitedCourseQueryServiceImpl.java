package com.odorok.OdorokApplication.course.service;

import com.odorok.OdorokApplication.diary.repository.VisitedCourseRepository;
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
}
