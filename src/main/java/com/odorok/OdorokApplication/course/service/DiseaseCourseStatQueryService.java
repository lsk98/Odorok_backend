package com.odorok.OdorokApplication.course.service;

import com.odorok.OdorokApplication.course.domain.DiseaseCourseStat;
import com.odorok.OdorokApplication.course.dto.response.item.RecommendedCourseSummary;

import java.util.List;

public interface DiseaseCourseStatQueryService {
    List<DiseaseCourseStat> queryDiseaseCourseStatFor(Long diseaseId);
}
