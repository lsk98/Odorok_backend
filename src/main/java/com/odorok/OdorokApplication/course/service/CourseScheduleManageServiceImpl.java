package com.odorok.OdorokApplication.course.service;

import com.odorok.OdorokApplication.course.dto.request.CourseScheduleRequest;
import com.odorok.OdorokApplication.course.exception.ScheduledDateOverlappingException;
import com.odorok.OdorokApplication.course.repository.ScheduledCourseRepository;
import com.odorok.OdorokApplication.domain.ScheduledCourse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseScheduleManageServiceImpl implements CourseScheduleManageService{
    private final ScheduledCourseRepository scheduledCourseRepository;
    private final ScheduledAttractionService scheduledAttractionService;

    @Override
    @Transactional
    public void registSchedule(CourseScheduleRequest request, Long userId) {

        if (checkOverlappedSchedule(request.getCourseId(), request.getDueDate(), userId)) {
            throw new ScheduledDateOverlappingException("날짜 " + request.getDueDate() + "에 중복되는 스케줄이 존재합니다.");
        }

        Long scheduleId = registCourseSchedule(request.getCourseId(), request.getDueDate(), userId);
        scheduledAttractionService.registAttractionSchedules(request.getAttractionIds(), scheduleId);
    }

    @Override
    public Long registCourseSchedule(Long courseId, LocalDateTime date, Long userId) {
        ScheduledCourse scheduledCourse = ScheduledCourse.builder().courseId(courseId).dueDate(date)
                .userId(userId).build();
        scheduledCourseRepository.save(scheduledCourse);
        return scheduledCourse.getId();
    }

    @Override
    public boolean checkOverlappedSchedule(Long courseId, LocalDateTime date, Long userId) {
        return scheduledCourseRepository.existsByCourseIdAndDueDateAndUserId(courseId, date, userId);
    }

}
