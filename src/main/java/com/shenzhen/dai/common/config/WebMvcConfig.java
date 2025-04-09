package com.shenzhen.dai.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web MVC配置类
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // SpringDoc OpenAPI 自动处理 Swagger UI 资源，无需手动配置
        // Knife4j 资源处理
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 允许跨域访问的路径
                .allowedOriginPatterns("*")  // 允许跨域访问的源
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // 允许请求方法
                .allowedHeaders("*")  // 允许头部设置
                .allowCredentials(true)  // 是否发送cookie
                .maxAge(3600);  // 跨域允许时间
    }
}
