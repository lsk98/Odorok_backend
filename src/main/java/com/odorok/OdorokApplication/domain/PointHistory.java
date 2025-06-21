package com.odorok.OdorokApplication.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "point_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointHistory {

    @Id
    private Long id;

    @Column(nullable = false)
    private Integer point;

    @Column(name = "aquired_at")
    private LocalDateTime aquiredAt;

    @Column(name = "attendance_id")
    private Long attendanceId;

    @Column(name = "article_id")
    private Long articleId;

    @Column(name = "comment_id")
    private Long commentId;

    @Column(name = "vcourse_id")
    private Long vcourseId;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}
