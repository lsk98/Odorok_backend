package com.odorok.OdorokApplication.course.dto.response.holder;

import com.odorok.OdorokApplication.course.dto.response.item.RecommendedCourseSummary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopRatedCourseResponse {
    private List<RecommendedCourseSummary> topStars;
    private List<RecommendedCourseSummary> topVisited;
    private List<RecommendedCourseSummary> topReviewCount;
}
