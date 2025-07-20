package com.odorok.OdorokApplication.course.dto.response.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiseaseAndCourses {
    private Long diseaseCode;
    private List<RecommendedCourseSummary> courses;
}
