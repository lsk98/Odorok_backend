package com.odorok.OdorokApplication.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "health_infos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealthInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Boolean gender; // TINYINT(1) → boolean/Boolean으로 매핑

    private Double height;

    private Double weight;

    private Integer age;

    private Boolean smoking; // TINYINT(1)

    @Column(name = "drink_per_week")
    private Integer drinkPerWeek;

    @Column(name = "exercise_per_week")
    private Integer exercisePerWeek;
}
