package com.saat.auto.cafe.auth.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by micahcoletti on 7/22/16.
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class, CassandraDataAutoConfiguration.class, HazelcastAutoConfiguration.class})
@EnableSwagger2
public class Application {

    public static void main(String[] args) {
        // This bootstraps Spring to run your app.
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Docket apiDocumentation() {
        // Swagger uses this to create the documentation.
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("")
                .description("")
                .termsOfServiceUrl("").contact(new Contact("", "", ""))
                .license("")
                .licenseUrl("")
                .version("1.0")
                .build();
    }
}
