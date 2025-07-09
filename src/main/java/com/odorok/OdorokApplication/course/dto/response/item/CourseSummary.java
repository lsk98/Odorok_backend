package com.odorok.OdorokApplication.course.dto.response.item;

import com.odorok.OdorokApplication.infrastructures.domain.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseSummary {
    private Long courseId; // : 1,
    private String courseIdx; // : 'string',
    private String gilName; // : '길 이름',
    private String courseName; // : '코스 이름',
    private Integer sidoCode; // : 1, // 시도 번호
    private Integer sigunguCode; // : 1, // 시군구 번호
    private Double distance; // : 10.2, // 거리
    private Double reqTime; // : 2.4, // 예상 소요 시간
    private Integer level; // : 1, // 난이도
    private Boolean cycle; // : true, // 순환형 여부
    private Boolean brdDiv; // : false, // 자전거 도로 여부.
    private LocalDate createdAt; // : '2023-12-31', // 등록일
    private LocalDate modifiedAt; // : '2024-03-01', // 수정일
    private Integer reward; // : 300, // 보상 점수
    private Boolean visited; // : false // 방문 여부

    public CourseSummary(Course course) {
        this.courseId = course.getId();
        this.courseIdx = course.getIdx();
        this.courseName = course.getName();
        this.sidoCode = course.getSidoCode();
        this.sigunguCode = course.getSigunguCode();
        this.distance = course.getDistance();
        this.reqTime = course.getReqTime();
        this.level = course.getLevel();
        this.cycle = course.getCycle();
        this.brdDiv = course.getBrdDiv();
        this.createdAt = (course.getCreatedAt() != null) ? course.getCreatedAt().toLocalDate() : null;
        this.modifiedAt = (course.getModifiedAt() != null) ? course.getModifiedAt().toLocalDate() : null;
        this.reward = course.getReward();
    }
}
