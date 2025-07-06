package com.odorok.OdorokApplication.course.service;

import com.odorok.OdorokApplication.course.dto.response.item.Coord;

import java.util.List;

public interface PathCoordQueryService {
    List<Coord> queryCoursePathCoords(Long courseId);
}
