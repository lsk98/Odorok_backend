package com.odorok.OdorokApplication.infrastructures.service;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.util.Map;

public interface DurunubiDataService {
    // 두루누비 길 데이터 로드
    void loadGilDatas();
    // 두루누비 코스 데이터 로드
    Map<Long, String> loadCourseDatas();

    // 두루누비 좌표 데이터 로드
    KakaoMapApiService.RegionInfo loadGPX(String url, Long crsId) throws ParserConfigurationException, SAXException;

    void setRegionInfo(Long courseId, KakaoMapApiService.RegionInfo region);
}
