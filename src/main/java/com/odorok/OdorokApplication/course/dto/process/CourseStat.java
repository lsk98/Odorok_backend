package com.odorok.OdorokApplication.course.dto.process;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseStat {
    private Long courseId;
    private Double avgStars;
    private Long reviewCount;

    @QueryProjection
    public CourseStat(Long courseId, Double avgStars, Long reviewCount) {
        this.courseId = courseId;
        this.avgStars = avgStars;
        this.reviewCount = reviewCount;
    }
}
