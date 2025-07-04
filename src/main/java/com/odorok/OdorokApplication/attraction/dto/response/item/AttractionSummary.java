package com.odorok.OdorokApplication.attraction.dto.response.item;

import com.odorok.OdorokApplication.draftDomain.Attraction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttractionSummary {
    private Long attrationId;
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

    public void summarize(Attraction attraction) {
        if(attraction == null) return;
        this.attrationId = attraction.getId();
        this.title = attraction.getTitle();
        this.firstImage1 = attraction.getFirstImage1();
        this.firstImage2 = attraction.getFirstImage2();
        this.latitude = attraction.getLatitude();
        this.longitude = attraction.getLongitude();
        this.tel = attraction.getTel();
        this.addr1 = attraction.getAddr1();
        this.addr2 = attraction.getAddr2();
        this.mapLevel = attraction.getMapLevel();
        this.homepage = attraction.getHomepage();
    }
}
