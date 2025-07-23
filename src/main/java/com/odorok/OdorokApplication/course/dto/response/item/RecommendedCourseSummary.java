package com.odorok.OdorokApplication.course.dto.response.item;

import com.odorok.OdorokApplication.infrastructures.domain.Course;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecommendedCourseSummary extends CourseSummary{
    private Integer avgStars;
    private Integer reviewCount;
    private Long visitationCount;
    public RecommendedCourseSummary(Course course, Integer avgStars, Integer reviewCount, Long visitationCount) {
        super(course);
        this.avgStars = avgStars;
        this.reviewCount = reviewCount;
        this.visitationCount = visitationCount;
    }

    @Override
    public String toString() {
        return "Rec_Course_Summary(courseId = %d, averageStars = %d,  reviewCount = %d, visitationCount = %d)".formatted(this.getCourseId(), this.getAvgStars(), this.getReviewCount(), this.getVisitationCount());
    }
}
