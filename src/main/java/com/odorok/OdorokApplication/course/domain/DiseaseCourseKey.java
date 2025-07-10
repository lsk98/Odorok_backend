package com.odorok.OdorokApplication.course.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class DiseaseCourseKey {
    @Column(name = "disease_id")
    private Long diseaseId;

    @Column(name = "course_id")
    private Long courseId;
}
