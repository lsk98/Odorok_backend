package com.odorok.OdorokApplication.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "scheduled_attractions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduledAttraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "attraction_id", nullable = false)
    private Long attractionId;

    @Column(name = "scourse_id", nullable = false)
    private Long scourseId;
}