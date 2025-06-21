package com.odorok.OdorokApplication.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "visited_attractions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VisitedAttraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "attraction_id", nullable = false)
    private Long attractionId;

    @Column(name = "vcourse_id", nullable = false)
    private Long vcourseId;
}
