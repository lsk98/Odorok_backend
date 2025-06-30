package com.odorok.OdorokApplication.course.controller;

import com.odorok.OdorokApplication.course.dto.response.item.CourseSummary;
import com.odorok.OdorokApplication.course.service.CourseQueryService;
import com.odorok.OdorokApplication.security.config.APISecurityConfig;
import com.odorok.OdorokApplication.security.config.OAuth2Config;
import com.odorok.OdorokApplication.security.domain.User;
import com.odorok.OdorokApplication.security.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = { CourseApiController.class })
@Import({APISecurityConfig.class, OAuth2Config.class})
@AutoConfigureMockMvc(addFilters = false)
class CourseApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CourseQueryService courseQueryService;
    @MockitoBean
    private UserService userService;

    @Test
    public void 가입된_사용자가_지역_코드로_코스_조회에_성공한다() throws Exception {
        // given
        int sidoCode = 1, sigunguCode = 1;
        Mockito.when(userService.selectByEmail(Mockito.anyString())).thenReturn(User.builder().id(1L).build());
        Mockito.when(courseQueryService.findCoursesByRegion(sidoCode, sigunguCode, 1L, Mockito.any())).thenReturn(List.of(
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
                        .param("email", "e").param("page", "0").param("size", "10").accept(MediaType.APPLICATION_JSON));

        // then
        result.andExpect(status().isOk()).andExpect(jsonPath("$.data.items[0].courseId").value(1l));
    }
}