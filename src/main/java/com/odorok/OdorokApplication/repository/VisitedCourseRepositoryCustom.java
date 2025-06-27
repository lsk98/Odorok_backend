package com.odorok.OdorokApplication.repository;

import com.odorok.OdorokApplication.diary.dto.gpt.VisitedAttraction;
import com.odorok.OdorokApplication.diary.dto.gpt.VisitedCourseAndAttraction;

import java.util.List;

public interface VisitedCourseRepositoryCustom {
    List<VisitedAttraction> findVisitedAttractionByVisitedCourseId(Long userId, Long visitedCourseId);
    VisitedCourseAndAttraction findCourseAndAttractionsByVisitedCourseId(Long userId, Long visitedCourseId);
}
