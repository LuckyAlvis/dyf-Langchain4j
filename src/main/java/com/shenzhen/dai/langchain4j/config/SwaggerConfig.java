package com.shenzhen.dai.langchain4j.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI文档配置类
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Langchain4j API文档")
                        .description("Langchain4j项目API文档")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Trae AI")
                                .email("")))
                .schemaRequirement("Authorization", new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT"));
    }
}
