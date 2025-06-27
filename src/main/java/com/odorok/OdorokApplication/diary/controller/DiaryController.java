package com.odorok.OdorokApplication.diary.controller;

import com.odorok.OdorokApplication.commons.exception.BadRequestException;
import com.odorok.OdorokApplication.commons.exception.GptCommunicationException;
import com.odorok.OdorokApplication.commons.exception.NotFoundException;
import com.odorok.OdorokApplication.diary.dto.response.DiaryChatResponse;
import com.odorok.OdorokApplication.diary.dto.response.DiaryDetail;
import com.odorok.OdorokApplication.diary.dto.response.DiaryPermissionCheckResponse;
import com.odorok.OdorokApplication.diary.service.DiaryService;
import com.odorok.OdorokApplication.gpt.service.GptService;
import com.odorok.OdorokApplication.security.principal.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/api/diaries")
@RequiredArgsConstructor
@RestController
@Slf4j
public class DiaryController {

    private final DiaryService diaryService;

    @GetMapping("/{diaryId}")
    public ResponseEntity<?> searchDiaryById(@PathVariable long diaryId, @AuthenticationPrincipal CustomUserDetails user) {
        long userId = user.getUser().getId();
        DiaryDetail diary = diaryService.findDiaryById(userId, diaryId);
        if(diary == null) {
            throw new NotFoundException("해당 일지를 찾을 수 없습니다.");
        }
        return handleSuccess(Map.of("diary", diary), HttpStatus.OK);
    }

    @GetMapping("/permission")
    public ResponseEntity<?> searchDiaryGeneratePermission(@AuthenticationPrincipal CustomUserDetails user) {
        long userId = user.getUser().getId();
        DiaryPermissionCheckResponse response = diaryService.findDiaryPermission(userId);
        return handleSuccess(Map.of("permission", response), HttpStatus.OK);
    }

    @GetMapping("/generation/{visitedCourseId}")
    public ResponseEntity<?> registGeneration(@RequestParam String style,
                                              @PathVariable Long visitedCourseId,
                                              @AuthenticationPrincipal CustomUserDetails user) {
        if (style == null || style.isBlank()) {
            throw new BadRequestException("스타일은 필수 입력값입니다.");
        }

        long userId = user.getUser().getId();

        DiaryChatResponse response =  diaryService.insertGeneration(userId, style, visitedCourseId);
        return handleSuccess(response, HttpStatus.OK, "IN_PROGRESS");
    }

    ResponseEntity<?> handleSuccess(Object data, HttpStatus status, String message) {
        Map<String, Object> map = Map.of("status", message, "data", data);
        return ResponseEntity.status(status).body(map);
    }

    ResponseEntity<?> handleSuccess(Object data, HttpStatus status) {
        return handleSuccess(data, status, status.name());
    }

}
