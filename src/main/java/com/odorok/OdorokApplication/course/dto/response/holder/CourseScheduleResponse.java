package com.odorok.OdorokApplication.course.dto.response.holder;

import com.odorok.OdorokApplication.course.dto.response.item.VisitationScheduleSummary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseScheduleResponse {
    private List<VisitationScheduleSummary> schedule;
}
