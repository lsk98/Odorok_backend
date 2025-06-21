package com.odorok.OdorokApplication.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "diary_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiaryImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "diary_id", nullable = false)
    private Long diaryId;

    @Column(name = "img_url", length = 500)
    private String imgUrl;
}