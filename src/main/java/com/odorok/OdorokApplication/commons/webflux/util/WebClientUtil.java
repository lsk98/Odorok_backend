package com.odorok.OdorokApplication.commons.webflux.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Slf4j
public class WebClientUtil {
    public static String doGetBlock(WebClient client, String path, Map<String, String> params) {
        log.debug("WEBCLIENT REQUEST URL = {}", path);
        return client.get().uri(builder -> {
                    builder = builder.path(path);
                    for (String key : params.keySet()) {
                        builder = builder.queryParam(key, params.get(key));
                    }
                    String url = builder.build().toString();
                    log.debug("webClient url : {}", url);
                    return builder.build();
                })
                .accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(String.class).block();
    }

    public static String doGetBlockWithHeaders(WebClient client, String path, Map<String, String> params, Map<String, String> headers) {
        log.debug("WEBCLIENT REQUEST URL = {}", path);
        return client.get().uri(builder -> {
                    builder = builder.path(path);
                    for (String key : params.keySet()) {
                        builder = builder.queryParam(key, params.get(key));
                    }
                    String url = builder.build().toString();
                    log.debug("webClient url : {}", url);
                    return builder.build();
                }).headers(headerConsumer -> {
                    for(String key : headers.keySet()) {
                        headerConsumer.add(key, headers.get(key));
                    }
                })
                .accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(String.class).block();
    }
}

/*
    1. WebClientConfig에 @Bean으로 전용 WebClient 인스턴스를 등록한다.
    2. 사용처에서 의존성 주입을 받는다.
    3. WebClientUtil의 doGetBlock(주입받은 클라이언트 객체, 경로 Path, 파라미터 Map)를 전달하고 내용을 String으로 받아온다.
 */