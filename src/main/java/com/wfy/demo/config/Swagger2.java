package com.wfy.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//注解标示该类为配置类,@Configuation注解包含了@Component注解
@Configuration
//注解开启 swagger2 功能
@EnableSwagger2
public class Swagger2 {

    /**
     * 构建一个Docket类
     * @return
     */
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SPRING_WEB.SWAGGER_2)
                .apiInfo(apiInfo())//加载api信息
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wfy.demo.controller"))//选择需要监控的包
                .paths(PathSelectors.any())//对所有路径进行监控
                .build();
    }

    /**
     * 构建 api文档的详细信息方法
     * 这里配置的信息都会显示在页面上
     * @return
     */
    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("项目Demo Api")//页面标题
                .description("demo后台API文档")//文档描述
                .contact(new Contact("wfy","localhost:8080","158154126@qq.com"))//创建人信息
                .version("1.0")//版本
                .build();
    }
}
