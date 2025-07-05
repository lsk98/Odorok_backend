package com.odorok.OdorokApplication.attraction.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Map;

@TestConfiguration
public class AttractionTestDataConfiguration {
    @Bean
    public Map<String, Integer> contentTypeMap() {
        return Map.of(
                "관광지", 12, "문화시설", 14,
                "축제공연행사", 15, "여행코스", 25,
                "레포츠", 28, "숙박", 32,
                "쇼핑", 38);
    }
}
