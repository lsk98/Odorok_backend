package com.odorok.OdorokApplication.region.controller;

import com.odorok.OdorokApplication.commons.response.CommonResponseBuilder;
import com.odorok.OdorokApplication.commons.response.ResponseRoot;
import com.odorok.OdorokApplication.course.service.CourseQueryService;
import com.odorok.OdorokApplication.region.dto.response.holder.SidoResponse;
import com.odorok.OdorokApplication.region.dto.response.holder.SigunguResponse;
import com.odorok.OdorokApplication.region.exception.InvalidSidoCodeException;
import com.odorok.OdorokApplication.region.service.RegionQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/api/regions")
@RequiredArgsConstructor
public class RegionApiController {
    private final RegionQueryService regionQueryService;
    private final CourseQueryService courseQueryService;

    @GetMapping("/sido")
    public ResponseEntity<ResponseRoot<SidoResponse>> getAllSidos() {
        Set<Integer> validSet = courseQueryService.queryValidSidoCodes();

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                .body(CommonResponseBuilder.success("시도 조회 성공",
                        new SidoResponse(
                                regionQueryService.queryAllSidos()
                                        .stream()
                                        .filter(i -> validSet.contains(i.getSidoCode())).toList()
                        )
                ));
    }

    @GetMapping("/sigungu")
    public ResponseEntity<ResponseRoot<SigunguResponse>> getAllSigunguOf(@RequestParam("sidoCode") Integer sidoCode) {
        Set<Integer> validSet = courseQueryService.queryValidSidoCodes();
        if(!validSet.contains(sidoCode)) {
            throw new InvalidSidoCodeException("유효하지 않은 시도 코드입니다 (sidoCode : " + sidoCode + ")");
        }
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                .body(CommonResponseBuilder.success("시군구 조회 성공", new SigunguResponse(regionQueryService.queryAllSigunguOf(sidoCode))));

    }
}
