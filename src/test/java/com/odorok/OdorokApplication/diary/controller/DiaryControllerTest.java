package com.odorok.OdorokApplication.diary.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.odorok.OdorokApplication.diary.dto.request.DiaryRequest;
import com.odorok.OdorokApplication.diary.dto.response.DiarySummary;
import com.odorok.OdorokApplication.diary.service.DiaryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DiaryController.class)
@AutoConfigureMockMvc(addFilters = false)
public class DiaryControllerTest {
    private final Long TEST_USER_ID = 1L;

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DiaryService diaryService;

    @Test
    void 일지_목록_조회_요청_성공() throws Exception {
        List<DiarySummary> diaryList = new ArrayList<>();
        final String testTitle1 = "일지 제목 1";
        diaryList.add(new DiarySummary(1L, testTitle1, LocalDateTime.of(2025, 1, 1, 12, 0, 0), LocalDateTime.of(2024, 12, 31, 0, 0, 0) ));
        diaryList.add(new DiarySummary(2L, "일지 제목 2", LocalDateTime.of(2025, 1, 3, 12, 0, 0), LocalDateTime.of(2024, 1, 2, 0, 0, 0) ));

        when(diaryService.findAllDiaryByUser(eq(TEST_USER_ID))).thenReturn(diaryList);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/diaries"));
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.data.length()").value(2));
        result.andExpect(jsonPath("$.data[0].id").value(1L));
        result.andExpect(jsonPath("$.data[0].title").value(testTitle1));
        result.andExpect(jsonPath("$.data[0].visitedAt").value("2024-12-31T00:00:00"));
    }

    @Test
    void 일지_연도별_그룹_목록_요청_성공() throws Exception {
        Map<String, List<DiarySummary>> diaryListGroupByYear = new HashMap<>();
        List<DiarySummary> diaryList2025 = new ArrayList<>();
        List<DiarySummary> diaryList2024 = new ArrayList<>();
        final String TestDiaryTitle = "일지 제목";
        diaryList2025.add(new DiarySummary(2L, "일지 제목 1", LocalDateTime.of(2025, 1, 3, 12, 0, 0), LocalDateTime.of(2025, 1, 2, 0, 0, 0)));
        diaryList2025.add(new DiarySummary(3L, "일지 제목 2", LocalDateTime.of(2025, 5, 3, 12, 12, 12), LocalDateTime.of(2025, 5, 4, 12, 12, 12)));
        diaryList2024.add(new DiarySummary(1L, TestDiaryTitle, LocalDateTime.of(2024, 11, 11, 0,0,0), LocalDateTime.of(2023, 11, 12, 0, 0, 0)));

        diaryListGroupByYear.put("2025", diaryList2025);
        diaryListGroupByYear.put("2024", diaryList2024);

        when(diaryService.findAllDiaryGroupByYear(eq(TEST_USER_ID))).thenReturn(diaryListGroupByYear);
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/diaries?groupBy=year"));

        result.andExpect(jsonPath("$.data['2025'].length()").value(2));
        result.andExpect(jsonPath("$.data['2024'].length()").value(1));
        result.andExpect(jsonPath("$.data['2024'].id").value(1L));
        result.andExpect(jsonPath("$.data['2024'].title").value(TestDiaryTitle));
        result.andExpect(jsonPath("$.data['2024'].createdAt").value("2024-11-11T00:00:00"));GIT
    }

    @Test
    void 일지_최종_저장_요청_성공() throws Exception {
        // given
        // diaryRequest mock
        DiaryRequest diaryRequest = new DiaryRequest(1L, "최종 일지 제목", "최종 일지 내용");
        String diaryJson = new ObjectMapper().writeValueAsString(diaryRequest);
        MockMultipartFile diaryPart = new MockMultipartFile(
                "diary",                      // 🔹 @RequestPart("diary")와 매칭됨
                "diary",                      // 🔹 filename은 중요하지 않음
                "application/json",          // 🔹 JSON MIME 타입
                diaryJson.getBytes(StandardCharsets.UTF_8)
        );

        // images mock
        MockMultipartFile image = new MockMultipartFile(
                "images",
                "test-image.jpg",
                "image/jpeg",
                "fake-image-content".getBytes()
        );

        Long savedDiaryId = 1L;
        when(diaryService.insertFinalizeDiary(eq(TEST_USER_ID), any(DiaryRequest.class), anyList())).thenReturn(savedDiaryId);

        // when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.multipart("/api/diaries")
                        .file(diaryPart)
                        .file(image)
                        .with(request -> {
                            request.setMethod("POST");
                            return request;
                        })
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        // .with(csrf()) // Spring Security 사용 시
                );
        // then
        result.andExpect(MockMvcResultMatchers.status().isCreated());
        result.andDo(MockMvcResultHandlers.print());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.data.diaryId").value(savedDiaryId));
    }

}
