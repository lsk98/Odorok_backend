package com.odorok.OdorokApplication.course.controller;

import com.odorok.OdorokApplication.course.dto.response.item.*;
import com.odorok.OdorokApplication.course.service.CourseQueryService;
import com.odorok.OdorokApplication.infrastructures.domain.Course;
import com.odorok.OdorokApplication.security.domain.User;
import com.odorok.OdorokApplication.security.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.util.UriComponentsBuilder;

import javax.xml.transform.Result;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = { CourseApiController.class })
@AutoConfigureMockMvc(addFilters = false)
class CourseApiControllerTest {
//    @MockitoBean
//    private JWTAuthenticationFilter jwtAuthenticationFilter;
//    @MockitoBean
//    private JWTVerificationFilter jwtVerificationFilter;

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CourseQueryService courseQueryService;
    @MockitoBean
    private UserService userService;

    private final static Long TEST_USER_ID = 1L;
    private final static String TEST_USER_EMAIL = "email";
    private final static Long TEST_COURSE_ID = 1L;


    @Test
    public void 가입된_사용자가_지역_코드로_코스_조회에_성공한다() throws Exception {
        // given
        int sidoCode = 1, sigunguCode = 1;
        Mockito.when(userService.selectByEmail(TEST_USER_EMAIL)).thenReturn(User.builder().id(TEST_USER_ID).build());
        Mockito.when(courseQueryService.queryCoursesByRegion(Mockito.eq(sidoCode), Mockito.eq(sigunguCode), Mockito.eq(1L), Mockito.any())).thenReturn(List.of(
                CourseSummary.builder()
                        .courseId(1l).courseName("코스1").gilName("GIL001")
                        .createdAt(LocalDateTime.now().toLocalDate()).modifiedAt(LocalDateTime.now().toLocalDate()).build(),
                CourseSummary.builder()
                        .courseId(2l).courseName("코스2").gilName("GIL002")
                        .createdAt(LocalDateTime.now().toLocalDate()).modifiedAt(LocalDateTime.now().toLocalDate()).build()
        ));

        // when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/courses/region")
                        .param("sidoCode", "1")
                        .param("sigunguCode", "1")
                        .param("email", TEST_USER_EMAIL).param("page", "0").param("size", "10").accept(MediaType.APPLICATION_JSON));

        // then
        result.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andExpect(jsonPath("$.data.items[0].courseId").value(1l));
    }

    @Test
    public void 전체코스_조회에_성공한다() throws Exception{
        Mockito.when(userService.selectByEmail(TEST_USER_EMAIL)).thenReturn(User.builder().id(TEST_USER_ID).build());
        Mockito.when(courseQueryService.queryAllCourses(Mockito.eq(TEST_USER_ID), Mockito.any(Pageable.class))).thenReturn(List.of(
                        CourseSummary.builder()
                                .courseId(1l).courseName("코스1").gilName("GIL001")
                                .createdAt(LocalDateTime.now().toLocalDate()).modifiedAt(LocalDateTime.now().toLocalDate()).build(),
                        CourseSummary.builder()
                                .courseId(2l).courseName("코스2").gilName("GIL002")
                                .createdAt(LocalDateTime.now().toLocalDate()).modifiedAt(LocalDateTime.now().toLocalDate()).build()
                ));


        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/courses")
                .param("size", "10").param("page", "0").param("email", TEST_USER_EMAIL));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.data.items[0].courseId").value(1L));
    }

    @Test
    public void 코스_상세_조회에_성공한다() throws Exception {
        Mockito.when(courseQueryService.queryCourseDetail(TEST_COURSE_ID))
                .thenReturn(new CourseDetail("요약", "전체", "여행", 6, 1000L, List.of(new Coord())));

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/courses/detail")
                .param("courseId", "1"));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.data.summary").value("요약"));
        resultActions.andExpect(jsonPath("$.data.contents").value("전체"));
        resultActions.andExpect(jsonPath("$.data.avgStars").value(6));
        resultActions.andExpect(jsonPath("$.data.reviewCount").value(1000));
        resultActions.andExpect(jsonPath("$.data.coords").isNotEmpty());
    }

    @Test
    public void 별점_상위_코스_조회에_성공한다() throws Exception {
        Mockito.when(courseQueryService.queryTopRatedCourses(Mockito.any(CourseQueryService.RecommendationCriteria.class)))
                .thenReturn(List.of(
                        new RecommendedCourseSummary(new Course(), 6, 10, 15L),
                        new RecommendedCourseSummary(new Course(), 7, 15, 20L),
                        new RecommendedCourseSummary(new Course(), 8, 15, 20L),
                        new RecommendedCourseSummary(new Course(), 9, 16, 20L),
                        new RecommendedCourseSummary(new Course(), 10, 20, 20L)
                ));

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/courses/top"));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.data.topStars").isNotEmpty());
        resultActions.andExpect(jsonPath("$.data.topVisited").isNotEmpty());
        resultActions.andExpect(jsonPath("$.data.topReviewCount").isNotEmpty());
        resultActions.andDo(MockMvcResultHandlers.print());

        Mockito.verify(courseQueryService, Mockito.times(3)).queryTopRatedCourses(Mockito.any(CourseQueryService.RecommendationCriteria.class));
    }

    @Test
    public void 질병_코스_조회에_성공한다() throws Exception {
        Mockito.when(userService.selectByEmail(Mockito.anyString())).thenReturn(User.builder().id(TEST_USER_ID).build());
        Mockito.when(courseQueryService.queryCoursesForDiseasesOf(Mockito.eq(TEST_USER_ID), Mockito.any(), Mockito.any(Pageable.class)))
                .thenReturn(List.of(
                        new DiseaseAndCourses(1L, List.of()),
                        new DiseaseAndCourses(3L, List.of()),
                        new DiseaseAndCourses(5L, List.of())
                ));

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/courses/disease")
                .param("email", "email"));
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.data.items").isNotEmpty());
        resultActions.andDo(MockMvcResultHandlers.print());

        Mockito.verify(courseQueryService, Mockito.times(1)).queryCoursesForDiseasesOf(Mockito.eq(TEST_USER_ID), Mockito.any(), Mockito.any(Pageable.class));
    }
}