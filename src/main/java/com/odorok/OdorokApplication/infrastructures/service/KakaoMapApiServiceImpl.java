package com.odorok.OdorokApplication.infrastructures.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.odorok.OdorokApplication.commons.webflux.util.WebClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
@Slf4j
public class KakaoMapApiServiceImpl implements KakaoMapApiService{
    private final WebClient client;
    private final String REST_KEY;
    private final String COORD_2_REGION_PATH = "/v2/local/geo/coord2regioncode.json";

    //curl -v -G GET "https://dapi.kakao.com/v2/local/geo/coord2regioncode.json?x=127.1086228&y=37.4012191" \
    //  -H "Authorization: KakaoAK ${REST_API_KEY}"

    public KakaoMapApiServiceImpl(@Autowired @Qualifier("kakaoClient") WebClient kakaoClient,
                                  @Value("${external.keys.kakao.rest-key}") String restKey) {
        this.client = kakaoClient;
        this.REST_KEY = restKey;
    }

    @Override
    public RegionInfo convertCoord2Region(Double latitude, Double longitude) {
        String response = WebClientUtil.doGetBlockWithHeaders(client, COORD_2_REGION_PATH,
                Map.of("x", longitude.toString(), "y", latitude.toString()),
                Map.of("Authorization", "KakaoAK "+REST_KEY));

        JsonObject root = JsonParser.parseString(response).getAsJsonObject();
        log.debug("root : {}", root.toString());
        JsonArray document = root.getAsJsonArray("documents");
        log.debug("document : {}", document.toString());
        JsonObject address = document.get(0).getAsJsonObject();
        log.debug("address : {}", address.toString());

        String sido = address.get("region_1depth_name").getAsString();
        String sigungu = address.get("region_2depth_name").getAsString().split(" ")[0];
        log.debug("KAKAO RESPONSE : sido name = {}, sigungu name = {} ", sido, sigungu);

        return new RegionInfo(sido, sigungu);
    }
}

/*
** SCHEME **
{
    "meta": {
            "total_count":1
    },
    "documents": [{
        "road_address": {
            "address_name": "서울특별시 종로구 북촌로 57",
            "region_1depth_name":"서울",
            "region_2depth_name":"종로구",
            "region_3depth_name":"",
            "road_name":"북촌로",
            "underground_yn":"N",
            "main_building_no":"57",
            "sub_building_no":"",
            "building_name":"천주교 가회동성당",
            "zone_no":"03052"
        },
        "address":{
            "address_name":"서울 종로구 가회동 30-3",
            "region_1depth_name":"서울",
            "region_2depth_name":"종로구",
            "region_3depth_name":"가회동",
            "mountain_yn":"N",
            "main_address_no":"30",
            "sub_address_no":"3",
            "zip_code":""
        }
    }]
}
 */