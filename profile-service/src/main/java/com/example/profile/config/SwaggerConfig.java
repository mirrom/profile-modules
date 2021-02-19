package com.example.profile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
    @Bean
    public Docket apiV1() {
        
        return new Docket(DocumentationType.SWAGGER_2).groupName("kex.base-v1").apiInfo(apiInfoV1()).select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
                .build();
    }
    
    private ApiInfo apiInfoV1() {
        
        return new ApiInfoBuilder().title("KEX.base Profile Service")
                .description("Profile handling for KEX.base knowledge database").version("1.0").build();
    }
    
}
