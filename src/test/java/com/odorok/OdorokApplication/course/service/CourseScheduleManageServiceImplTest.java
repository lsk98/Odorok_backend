package com.odorok.OdorokApplication.course.service;

import com.odorok.OdorokApplication.course.dto.request.CourseScheduleRequest;
import com.odorok.OdorokApplication.course.repository.ScheduledCourseRepository;
import com.odorok.OdorokApplication.domain.ScheduledCourse;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CourseScheduleManageServiceImplTest {
    @Autowired
    private CourseScheduleManageService courseScheduleManageService;
    @Autowired
    private ScheduledCourseRepository scheduledCourseRepository;
    @Autowired
    private ScheduledAttractionService scheduledAttractionService;

    // 아래 테스트는 Repeatable 하지 못한 관계로 새롭게 작성하여야 함.
//    @Test
//    public void 방문코스_등록에_성공한다() {
//        CourseScheduleRequest request = new CourseScheduleRequest();
//        request.setEmail("jihun@example.com");
//        request.setCourseId(1L);
//        request.setDueDate(LocalDateTime.now());
//        request.setAttractionIds(List.of(56644l, 56645l, 56646l));
//
//        courseScheduleManageService.registSchedule(request, 1L);
//
//        assertThat(scheduledCourseRepository.findByUserId(1L).size()).isOne();
//        ScheduledCourse sc = scheduledCourseRepository.findByUserId(1L).get(0);
//        assertThat(scheduledAttractionService.queryScheduledAttractions(sc.getId()).size()).isEqualTo(3);
//
//        scheduledAttractionService.deleteAttractionSchedules(sc.getId());
//        scheduledCourseRepository.deleteById(sc.getId());
//    }
}