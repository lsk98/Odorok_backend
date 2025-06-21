package com.odorok.OdorokApplication.commons.webflux.driver;

import com.odorok.OdorokApplication.infrastructures.service.DurunubiDataService;
import com.odorok.OdorokApplication.infrastructures.service.KakaoMapApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoadDurunubiDriver {
    private final DurunubiDataService durunubiDataService;

//    @EventListener(ApplicationReadyEvent.class)
//    public void loadDataFromApiServerOnce() throws ParserConfigurationException, SAXException {
//        log.debug("attempt to load datas from durunubi api -------------");
//        loadToLocalDB();
//    }

    public void loadToLocalDB() throws ParserConfigurationException, SAXException {
        durunubiDataService.loadGilDatas();
        Map<Long, String> gpxMap = durunubiDataService.loadCourseDatas();
        for(Long courseId : gpxMap.keySet()) {
            KakaoMapApiService.RegionInfo region = durunubiDataService.loadGPX(gpxMap.get(courseId), courseId);
            durunubiDataService.setRegionInfo(courseId, region);
        }
        log.debug("로컬 DB로 업데이트 완료!");
    }

    public void updateLocalDB() {
        throw new RuntimeException("아직 개발되지 않은 기능");
    }
}
