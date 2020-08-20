package cn.kevin.ims.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.function.Predicate;

/**
 * @Author: Kevin
 * @Date: 2020/8/20 4:35 下午
 */
@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .host("localhost:9988")
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.kevin.ims.controller"))
                .paths(Predicate.not(PathSelectors.regex("/error.*")))
                .paths(PathSelectors.regex("/.*"))
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("综合信息管理系统接口文档")
                .contact(new Contact("Kevin", "http://test.ims.cool", "jiazekai1003@gmail.com"))
                .description("Swagger动态接口文档")
                .license("The Apache License, Version 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("Demo")
                .build();
    }
}
