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
}
