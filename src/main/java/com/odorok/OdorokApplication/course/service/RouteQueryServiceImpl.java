package com.odorok.OdorokApplication.course.service;

import com.odorok.OdorokApplication.course.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RouteQueryServiceImpl implements RouteQueryService{
    private final RouteRepository routeRepository;

    @Override
    public String queryRouteNameByRouteIdx(String idx) {
        return routeRepository.findByIdx(idx)
                .orElseThrow(() -> new IllegalArgumentException("길 IDX가 존재하지 않습니다.(" + idx+ ")")).getName();
    }
}
