package com.odorok.OdorokApplication.course.dto.response.holder;

import com.odorok.OdorokApplication.course.dto.response.item.CourseSummary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {
    private List<CourseSummary> items;
}
