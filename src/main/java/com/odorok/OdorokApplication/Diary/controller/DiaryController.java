package com.odorok.OdorokApplication.Diary.controller;

import com.odorok.OdorokApplication.Diary.dto.DiaryDetail;
import com.odorok.OdorokApplication.Diary.service.DiaryService;
import com.odorok.OdorokApplication.domain.Diary;
import com.odorok.OdorokApplication.security.principal.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            return handleFail(new RuntimeException("해당 일지를 찾을 수 없습니다."), HttpStatus.NOT_FOUND);
        }
        return handleSuccess(Map.of("diary", diary), HttpStatus.OK);
    }

    ResponseEntity<?> handleSuccess(Object data, HttpStatus status) {
        Map<String, Object> map = Map.of("status", "SUCCESS", "data", data);
        return ResponseEntity.status(status).body(map);
    }

    ResponseEntity<?> handleFail(Exception e, HttpStatus status) {
        if (status.is5xxServerError()) {
            log.error(e.getMessage(), e); // 스택트레이스 포함
        } else {
            log.debug(e.getMessage());
        }
        Map<String, Object> map = Map.of("status", status.name(), "message", e.getMessage());
        return ResponseEntity.status(status).body(map);
    }
}
