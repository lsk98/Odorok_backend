package com.odorok.OdorokApplication.diary.dto.response;

import com.odorok.OdorokApplication.domain.Diary;
import com.odorok.OdorokApplication.domain.VisitedCourse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DiarySummary {
    private Long id;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime visitedAt;
}
