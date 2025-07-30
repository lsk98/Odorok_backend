package com.odorok.OdorokApplication.commons.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    private static final String TITLE = "Odorok API 문서";
    private static final String VERSION = "0.0.1";
    private static final String DESCRIPTION = "한국 관광공사 데이터 활용 공모전.";
    @Bean
    public OpenAPI openAPI() {
        final String securitySchemeName = "bearerAuth";

        Info info = new Info().title(TITLE).version(VERSION).description(DESCRIPTION);
        return new OpenAPI().info(info)
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName)) // API 전체에 보안 적용
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("JWT 기반 인증을 위한 토큰을 입력하세요.<br>" +
                                        "예: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
                        )
                );
    }
}
