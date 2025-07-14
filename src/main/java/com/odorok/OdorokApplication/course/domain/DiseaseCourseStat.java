package com.odorok.OdorokApplication.course.domain;

import com.odorok.OdorokApplication.course.dto.process.CourseStat;
import com.odorok.OdorokApplication.course.dto.process.CourseStatSummary;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Immutable
@Entity(name = "disease_course_stat")
public class DiseaseCourseStat implements CourseStatSummary {
    @EmbeddedId
    private DiseaseCourseKey key;

    @Column(name = "avg_stars", columnDefinition = "DECIMAL(14, 4)")
    private BigDecimal avgStars;

    @Column(name = "review_count")
    private Long reviewCount;

    @Column(name = "visitation_count")
    private Long visitationCount;

    @Override
    public Long getCourseId() {
        return this.key.getCourseId();
    }

    @Override
    public Double getAvgStars() {
        return this.avgStars.doubleValue();
    }

    @Override
    public Long getReviewCount() {
        return this.reviewCount;
    }

    @Override
    public Long getVisitationCount() {
        return this.visitationCount;
    }

    public DiseaseCourseKey getKey() {
        return key;
    }

    public void setKey(DiseaseCourseKey key) {
        this.key = key;
    }

    public void setAvgStars(BigDecimal avgStars) {
        this.avgStars = avgStars;
    }

    public void setReviewCount(Long reviewCount) {
        this.reviewCount = reviewCount;
    }

    public void setVisitationCount(Long visitationCount) {
        this.visitationCount = visitationCount;
    }
}
