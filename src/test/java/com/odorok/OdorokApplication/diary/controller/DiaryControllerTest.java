package com.odorok.OdorokApplication.diary.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.odorok.OdorokApplication.diary.dto.request.DiaryRequest;
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
import java.util.List;

import static org.mockito.Mockito.*;

@WebMvcTest(DiaryController.class)
@AutoConfigureMockMvc(addFilters = false)
public class DiaryControllerTest {
    private final Long TEST_USER_ID = 1L;

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DiaryService diaryService;

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
