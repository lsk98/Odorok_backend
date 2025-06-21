package com.odorok.OdorokApplication.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "scheduled_courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduledCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @Column(name = "due_date", nullable = false)
    private LocalDateTime dueDate;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}
