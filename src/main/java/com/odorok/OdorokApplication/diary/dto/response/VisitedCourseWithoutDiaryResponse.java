package com.odorok.OdorokApplication.diary.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor
public class VisitedCourseWithoutDiaryResponse {
    private List<VisitedCourseSummary> response;
}
