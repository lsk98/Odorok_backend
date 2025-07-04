package com.odorok.OdorokApplication.region.controller;

import com.odorok.OdorokApplication.commons.response.CommonResponseBuilder;
import com.odorok.OdorokApplication.commons.response.ResponseRoot;
import com.odorok.OdorokApplication.region.dto.response.holder.SidoResponse;
import com.odorok.OdorokApplication.region.dto.response.holder.SigunguResponse;
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

@Slf4j
@RestController
@RequestMapping("/api/regions")
@RequiredArgsConstructor
public class RegionApiController {
    private final RegionQueryService regionQueryService;

    @GetMapping("/sido")
    public ResponseEntity<ResponseRoot<SidoResponse>> getAllSidos() {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                .body(CommonResponseBuilder.success("시도 조회 성공", new SidoResponse(regionQueryService.queryAllSidos())));

    }

    @GetMapping("/sigungu")
    public ResponseEntity<ResponseRoot<SigunguResponse>> getAllSigunguOf(@RequestParam("sidoCode") Integer sidoCode) {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                .body(CommonResponseBuilder.success("시군구 조회 성공", new SigunguResponse(regionQueryService.queryAllSigunguOf(sidoCode))));

    }
}
