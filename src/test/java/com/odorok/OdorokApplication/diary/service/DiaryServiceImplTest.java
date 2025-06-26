package com.odorok.OdorokApplication.diary.service;

import com.odorok.OdorokApplication.diary.dto.DiaryDetail;
import com.odorok.OdorokApplication.diary.repository.DiaryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;



public class DiaryServiceImplTest {
    @Mock
    private DiaryRepository diaryRepository;

    @InjectMocks
    private DiaryServiceImpl diaryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void 일지_상세정보_조회_성공() {
        // given
        long userId = 1L;
        long diaryId = 2L;

        DiaryDetail mockDiaryDetail = new DiaryDetail(
                diaryId,
                "제목",
                "내용",
                List.of("img1.jpg", "img2.jpg"),
                userId,
                "제주도 코스",
                LocalDateTime.of(2024, 6, 1, 10, 0),
                LocalDateTime.of(2024, 6, 1, 12, 0)
        );

        when(diaryRepository.findDiaryById(userId, diaryId)).thenReturn(mockDiaryDetail);

        // when
        DiaryDetail result = diaryService.findDiaryById(userId, diaryId);

        // then
        assertNotNull(result);
        assertEquals("제목", result.getTitle());
        assertEquals(userId, result.getUserId());
        assertEquals("제주도 코스", result.getCourseName());
        assertEquals(2, result.getImgs().size());
        verify(diaryRepository, times(1)).findDiaryById(userId, diaryId);

    }
}
