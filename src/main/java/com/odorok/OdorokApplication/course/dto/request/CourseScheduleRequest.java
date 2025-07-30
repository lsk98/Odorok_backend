package com.odorok.OdorokApplication.course.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseScheduleRequest {
    Long courseId;
    LocalDateTime dueDate;
    List<Long> attractionIds = new ArrayList<>();
}
