package com.odorok.OdorokApplication.diary.service;

import com.odorok.OdorokApplication.diary.dto.response.DiaryDetail;
import com.odorok.OdorokApplication.diary.dto.response.DiaryPermissionCheckResponse;
import com.odorok.OdorokApplication.diary.repository.DiaryRepository;
import com.odorok.OdorokApplication.draftDomain.Inventory;
import com.odorok.OdorokApplication.repository.InventoryRepository;
import com.odorok.OdorokApplication.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class DiaryServiceImplTest {
    @Mock
    private DiaryRepository diaryRepository;

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private DiaryServiceImpl diaryService;

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

    @Test
    void 일지_생성권_조회_생성권이_있을_때_성공() {
        // given
        long userId = 1L;
        long itemId = 3L;

        ReflectionTestUtils.setField(diaryService, "diaryPermissionItemId", itemId);

        Inventory inventory = Inventory.builder()
                .userId(userId)
                .itemId(itemId)
                .count(2)
                .build();
        when(inventoryRepository.findByUserIdAndItemId(userId, itemId)).thenReturn(Optional.of(inventory));

        // when
        DiaryPermissionCheckResponse response = diaryService.findDiaryPermission(userId);

        //then
        assertEquals(userId, response.getUserId());
        assertEquals(itemId, response.getItemId());
        assertEquals(2, response.getCount());
        assertTrue(response.isCanCreateDiary());
    }

    @Test
    void 일지_생성권_조회_생성권이_없을_때_성공() {
        // given
        long userId = 2L;
        long itemId = 3L;

        ReflectionTestUtils.setField(diaryService, "diaryPermissionItemId", itemId);

        Inventory inventory = Inventory.builder()
                .userId(userId)
                .itemId(itemId)
                .count(0)
                .build();

        when(inventoryRepository.findByUserIdAndItemId(userId, itemId)).thenReturn(Optional.of(inventory));
        // when
        DiaryPermissionCheckResponse response = diaryService.findDiaryPermission(userId);

        //then
        assertEquals(userId, response.getUserId());
        assertEquals(itemId, response.getItemId());
        assertEquals(0, response.getCount());
        assertFalse(response.isCanCreateDiary());
    }
}
