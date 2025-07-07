package com.odorok.OdorokApplication.course.dto.response.item;

import com.odorok.OdorokApplication.infrastructures.domain.Course;
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
    private String contents; // : '전체 소개',
    private String travelerInfo; // : '여행자 정보 글',
    private Integer avgStars; // : 7, // 10까지 있으며 반 개 단위. 3 = 별 1개 반.
    private Long reviewCount; // : 102, //관련 리뷰 수
    private List<Coord> coords; // :

    public CourseDetail(Course course) {
        this.summary = course.getSummary();
        this.contents = course.getContents();
        this.travelerInfo = course.getTravelerInfo();
    }
}
