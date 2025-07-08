package com.odorok.OdorokApplication.course.dto.response.item;

import com.odorok.OdorokApplication.infrastructures.domain.Course;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendedCourseSummary extends CourseSummary{
    private Integer avgStars;
    private Integer reviewCount;

    public RecommendedCourseSummary(Course course, Integer avgStars, Integer reviewCount) {
        super(course);
        this.avgStars = avgStars;
        this.reviewCount = reviewCount;
    }
}
