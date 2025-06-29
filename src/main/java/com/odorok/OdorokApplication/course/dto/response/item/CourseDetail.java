package com.odorok.OdorokApplication.course.dto.response.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDetail {
    private String summary; // : '요약 소개',
    private String conents; // : '전체 소개',
    private String travelerInfo; // : '여행자 정보 글',
    private Integer avgStars; // : 7, // 10까지 있으며 반 개 단위. 3 = 별 1개 반.
    private Integer reviewCount; // : 102, //관련 리뷰 수
    private List<Coord> coords; // :
}
