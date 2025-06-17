package com.odorok.OdorokApplication.commons.webflux.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
@Slf4j
public class WebClientConfig {

    @Bean("gptClient")
    public WebClient gptClient(WebClient.Builder builder) {
        return builder.build();
    }

    @Bean("durunubiClient")
    public WebClient dataClient(WebClient.Builder builder) {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory("https://apis.data.go.kr");
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY); // 또는 VALUES_ONLY
        return builder.uriBuilderFactory(factory).codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024)).build();
    }
}
