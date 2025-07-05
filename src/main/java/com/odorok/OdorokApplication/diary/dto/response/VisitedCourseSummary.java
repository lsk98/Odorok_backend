package com.odorok.OdorokApplication.diary.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class VisitedCourseSummary {
    private Long id;
    private LocalDateTime visitedAt;
    private String courseName;
}
