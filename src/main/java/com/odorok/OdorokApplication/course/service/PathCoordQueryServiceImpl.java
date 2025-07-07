package com.odorok.OdorokApplication.course.service;

import com.odorok.OdorokApplication.course.dto.response.item.Coord;
import com.odorok.OdorokApplication.course.repository.PathCoordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PathCoordQueryServiceImpl implements PathCoordQueryService{
    private final PathCoordRepository pathCoordRepository;


    @Override
    public List<Coord> queryCoursePathCoords(Long courseId) {
        return pathCoordRepository.findByCourseId(courseId).stream().map(Coord::new).toList();
    }
}
