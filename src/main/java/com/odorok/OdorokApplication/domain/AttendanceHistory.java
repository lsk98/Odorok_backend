package com.odorok.OdorokApplication.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "attendance_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class  AttendanceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "attended_at", nullable = false)
    private LocalDateTime attendedAt;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}