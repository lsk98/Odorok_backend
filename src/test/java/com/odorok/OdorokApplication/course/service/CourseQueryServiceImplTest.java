package com.odorok.OdorokApplication.course.service;

import com.odorok.OdorokApplication.course.dto.response.item.CourseSummary;
import com.odorok.OdorokApplication.course.repository.CourseRepository;
import com.odorok.OdorokApplication.infrastructures.domain.Course;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CourseQueryServiceImplTest {
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private RouteQueryService routeQueryService;
    @Mock
    private VisitedCourseQueryService visitedCourseQueryService;
    @Mock
    private Page<Course> mockPage;

    @InjectMocks
    private CourseQueryServiceImpl courseQueryService;

    private static Long TEST_USER_ID = 1L;

    @Test
    public void 시군구_코드로_코스_조회에_성공한다() {
        // given : Method stub
        int sidoCode = 1, sigunguCode = 1;
        Mockito.when(mockPage.getContent()).thenReturn(List.of(
                Course.builder().id(1l).name("코스1").routeIdx("GIL001").createdAt(LocalDateTime.now()).modifiedAt(LocalDateTime.now()).build(),
                Course.builder().id(2l).name("코스2").routeIdx("GIL002").createdAt(LocalDateTime.now()).modifiedAt(LocalDateTime.now()).build()
        ));
        Mockito.when(courseRepository.findBySidoCodeAndSigunguCode(Mockito.eq(sidoCode), Mockito.eq(sigunguCode), Mockito.any()))
                .thenReturn(mockPage);
        Mockito.when(routeQueryService.queryRouteNameByRouteIdx(Mockito.anyString())).thenReturn("길");
        Mockito.when(visitedCourseQueryService.checkVisitedCourse(Mockito.eq(TEST_USER_ID), Mockito.anyLong())).thenReturn(Boolean.FALSE);

        // when
        List<CourseSummary> res = courseQueryService.findCoursesByRegion(sidoCode, sigunguCode, TEST_USER_ID, Pageable.ofSize(10));

        // then
        assertNotNull(res);
        assertNotEquals( 0, res.size());
        Mockito.verify(courseRepository, Mockito.times(1)).findBySidoCodeAndSigunguCode(Mockito.eq(sidoCode), Mockito.eq(sigunguCode), Mockito.any(Pageable.class));
        Mockito.verify(routeQueryService, Mockito.times(2)).queryRouteNameByRouteIdx(Mockito.anyString());
        Mockito.verify(visitedCourseQueryService, Mockito.times(2)).checkVisitedCourse(Mockito.eq(TEST_USER_ID), Mockito.anyLong());

    }
}