package com.odorok.OdorokApplication.diary.dto.gpt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class VisitedCourseAndAttraction {
    private String courseName;
    private String courseSummary;
    @Setter
    private List<VisitedAttraction> visitedAttractions;

    String attractionsToString() {
        return visitedAttractions.stream()
                .map(VisitedAttraction::getTitle) // 명소 이름만 추출
                .distinct()
                .collect(Collectors.joining(", "));
    }
}