package com.odorok.OdorokApplication.commons.webflux.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.Map;

@Slf4j
public class WebClientUtil {
    public static String doGetBlock(WebClient client, String baseUrl, Map<String, String> params) {
        UriComponentsBuilder builder =  UriComponentsBuilder.fromUriString(baseUrl);

        if(params != null) {
            for (String key : params.keySet()) {
                builder = builder.queryParam(key, params.get(key));
            }
        }

        String url = builder.build().encode().toUriString();

        log.debug("WEBCLIENT REQUEST URL = {}", url);
        return client.get().uri(url).accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(String.class).block();
    }

}
