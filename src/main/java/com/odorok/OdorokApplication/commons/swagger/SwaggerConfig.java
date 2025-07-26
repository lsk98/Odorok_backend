package com.odorok.OdorokApplication.commons.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    private static final String TITLE = "Odorok API 문서";
    private static final String VERSION = "0.0.1";
    private static final String DESCRIPTION = "한국 관광공사 데이터 활용 공모전.";
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info().title(TITLE).version(VERSION).description(DESCRIPTION);
        return new OpenAPI().components(new Components()).info(info);
    }
}
