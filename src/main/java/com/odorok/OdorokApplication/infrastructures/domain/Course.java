package com.odorok.OdorokApplication.infrastructures.domain;

import com.google.gson.JsonObject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course implements JsonSettable {
    public final String PN_ROUTE_IDX = "routeIdx";
    public final String PN_CRS_IDX = "crsIdx";
    public final String PN_CRS_KOR_NM = "crsKorNm";
    public final String PN_CRS_DSTNC = "crsDstnc";
    public final String PN_CRS_TOTL_RQRMHOUR = "crsTotlReqHour";
    public final String PN_CRS_LEVEL = "crsLevel";
    public final String PN_CRS_CYCLE = "crsCycle";
    public final String PN_CRS_CONTENTS = "crsContents";
    public final String PN_CRS_SUMMARY = "crsSummary";
    public final String PN_CRS_TOUR_INFO = "crsTourInfo";
    public final String PN_TRAVELER_INFO = "travelerinfo";
    public final String PN_SIGUN = "sigun";
    public final String PN_BRD_DIV = "brdDiv";
    public final String PN_CREATED_TIME = "createdtime";
    public final String PN_MODIFIED_TIME = "modifiedtime";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    @Column(name = "id")
    private Long id;

    @Column(name = "idx")
    private String idx;

    @Column(name = "distance")
    private Double distance;

    @Column(name = "name")
    private String name;

    @Column(name = "level")
    private Integer level;

    @Column(name = "line_msg")
    private String lineMsg;

    @Column(name = "overview")
    private String overview;

    @Column(name = "brd_div")
    private Boolean brdDiv;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "route_idx")
    private String routeIdx;

    @Column(name = "sido_code")
    private Integer sidoCode;

    @Column(name = "sigungu_code")
    private Integer sigunguCode;


    @Override
    public void setWithJson(JsonObject object) {
        this.idx = object.get(PN_CRS_IDX).getAsString();
        this.routeIdx = object.get(PN_ROUTE_IDX).getAsString();
        this.distance = object.get(PN_CRS_DSTNC).getAsDouble();
        this.name = object.get(PN_CRS_KOR_NM).getAsString();
        this.level = object.get(PN_CRS_LEVEL).getAsInt();
        this.lineMsg = object.get(PN_CRS_SUMMARY).getAsString();
        this.overview = object.get(PN_CRS_CONTENTS).getAsString();

        // 자전거 길 : DNBW
        this.brdDiv = object.get(PN_BRD_DIV).getAsString().equals("DNBW");
        this.createdAt = convertDateType(object.get(PN_CREATED_TIME).getAsString());
        this.modifiedAt = convertDateType(object.get(PN_MODIFIED_TIME).getAsString());
    }
}
