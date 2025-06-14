package com.odorok.OdorokApplication.commons.webflux.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Configuration
@Slf4j
public class WebClientConfig {

    @Bean("gptClient")
    public WebClient webClient(WebClient.Builder builder) {
        return builder.baseUrl("요청을 날릴 URL")
                .defaultHeaders(
                        customizer ->
                                customizer.add(HttpHeaders.CONTENT_TYPE, "application/json")
                )
                .defaultCookie("my cookie", "value1", "value2")
                .defaultUriVariables(Map.of("id", "1", "name", "이원준"))
                .build();
    }
}
