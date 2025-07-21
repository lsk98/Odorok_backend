package com.odorok.OdorokApplication.course.service;

import com.odorok.OdorokApplication.course.dto.response.item.VisitationScheduleSummary;
import com.odorok.OdorokApplication.course.repository.CourseRepository;
import com.odorok.OdorokApplication.course.repository.ScheduledAttractionRepository;
import com.odorok.OdorokApplication.course.repository.ScheduledCourseRepository;
import com.odorok.OdorokApplication.domain.ScheduledCourse;
import com.odorok.OdorokApplication.infrastructures.domain.Course;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CourseScheduleQueryServiceImplTest {
    @Mock
    private ScheduledCourseRepository scheduledCourseRepository;
    @Mock
    private RouteQueryService routeQueryService;
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private ScheduledAttractionRepository scheduledAttractionRepository;

    @InjectMocks
    private CourseScheduleQueryServiceImpl courseScheduleQueryService;

    private static final Long TEST_USER_ID = 1L;
    private static final Long TEST_COURSE_ID = 1L;
    private static final Long TEST_SCHEDULED_COURSE_ID = 1L;
    private static final String TEST_ROUTE_IDX = "TEST_ROUTE_IDX";
    private static final String TEST_COURSE_NAME = "TEST_COURSE_NAME";
    private static final String TEST_ROUTE_NAME = "TEST_ROUTE_NAME";

    @Test
    public void 방문예정_코스_조회에_성공한다() {
        Mockito.when(scheduledCourseRepository.findByUserId(TEST_USER_ID)).thenReturn(List.of(
                ScheduledCourse.builder().id(TEST_SCHEDULED_COURSE_ID)
                        .courseId(TEST_COURSE_ID)
                        .userId(TEST_USER_ID)
                        .dueDate(LocalDateTime.now()).build()));
        Mockito.when(courseRepository.findById(TEST_COURSE_ID)).thenReturn(
                Optional.of(Course.builder().id(TEST_COURSE_ID).routeIdx(TEST_ROUTE_IDX).name(TEST_COURSE_NAME).build()));
        Mockito.when(routeQueryService.queryRouteNameByRouteIdx(TEST_ROUTE_IDX)).thenReturn(TEST_ROUTE_NAME);
        Mockito.when(scheduledAttractionRepository.countByScourseId(TEST_SCHEDULED_COURSE_ID)).thenReturn(3L);

        List<VisitationScheduleSummary> summaries = courseScheduleQueryService.queryAllSchedule(TEST_USER_ID);

        assertThat(summaries.size()).isOne();
        assertThat(summaries.get(0).getCourseName()).isEqualTo(TEST_COURSE_NAME);
        assertThat(summaries.get(0).getGilName()).isEqualTo(TEST_ROUTE_NAME);
        assertThat(summaries.get(0).getAttractionCount()).isEqualTo(3);
    }
}