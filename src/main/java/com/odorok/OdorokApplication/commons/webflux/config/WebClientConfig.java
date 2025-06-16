package com.odorok.OdorokApplication.commons.webflux.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Slf4j
public class WebClientConfig {

    @Bean("gptClient")
    public WebClient gptClient(WebClient.Builder builder) {
        return builder.build();
    }

    @Bean("durunubiClient")
    public WebClient dataClient(WebClient.Builder builder) {
        return builder.build();
    }
}
