package com.odorok.OdorokApplication.course.dto.response.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VisitationScheduleSummary { // 방문 예정 코스 조회시.
    private LocalDateTime dueDate;
    private String gilName;
    private String courseName;
    private Long attractionCount;
}
