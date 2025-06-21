package com.odorok.OdorokApplication.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "achievement_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AchievementHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "achievement_id", nullable = false)
    private Integer achievementId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "achieved_at", nullable = false)
    private LocalDateTime achievedAt;
}
