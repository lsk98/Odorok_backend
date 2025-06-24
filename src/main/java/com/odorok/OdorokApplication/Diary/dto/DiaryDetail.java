package com.odorok.OdorokApplication.Diary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class DiaryDetail {
    private Long id;
    private String title;
    private String content;
    @Setter
    private List<String> imgs;
    private Long userId;
    private String courseName;
    private LocalDateTime visitedAt;
    private LocalDateTime createdAt;
}
