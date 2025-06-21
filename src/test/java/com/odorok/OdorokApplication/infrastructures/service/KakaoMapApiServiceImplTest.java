package com.odorok.OdorokApplication.infrastructures.service;

import com.odorok.OdorokApplication.draftDomain.Sido;
import com.odorok.OdorokApplication.draftDomain.Sigungu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KakaoMapApiServiceImplTest {
    @Autowired
    private KakaoMapApiService kakaoMapApiService;

    @Autowired
    private RegionServiceTemp regionServiceTemp;

    @Test
    public void 카카오_맵_좌표_2_행정동_API_호출_테스트() {
        double latitude = 37.1507494904;
        double longitude = 129.2062296318;
        KakaoMapApiService.RegionInfo info = kakaoMapApiService.convertCoord2Region(latitude, longitude);
        System.out.println(info);
        Sido sido = regionServiceTemp.findSidoByShortExp(info.getSido());
        Sigungu sigungu = regionServiceTemp.findSigunguByShortExp(sido.getCode(), info.getSigungu());

        System.out.println(sido);
        System.out.println(sigungu);
    }
}