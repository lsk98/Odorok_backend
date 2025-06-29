package com.odorok.OdorokApplication.course.dto.response.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttractionSummary {
    private Long attractionId;
    private String title;
    private String firstImage1;
    private String firstImage2;
    private Double latitude;
    private Double longitude;
    private String tel;
    private String addr1;
    private String addr2;
    private Integer mapLevel;
    private String homepage;
}
