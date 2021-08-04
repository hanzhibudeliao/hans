package com.lousland.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {

    // http://localhost:8081/doc.html

    // 配置swagger2核心配置 docket
    @Bean
    public Docket createRestApi() {

        /*
        1.制定api类型为SWAGGER2
        2.apiInfo 用于定义api文档汇总信息
        3.basePackage 用于指定controller包
        4.PathSelectors.any 用于指定包下所有的类
         */
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lousland.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("lousland api")
                .contact(new Contact("hans", "lousland.com", "prickhan0722@gmail.com"))
                .description("Provide api documentation for lousland.com")
                .version("0.0.9")
                .termsOfServiceUrl("https://www.loualand.com")
                .build();
    }
}
