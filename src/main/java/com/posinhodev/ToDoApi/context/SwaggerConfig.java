package com.posinhodev.ToDoApi.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * Se habilita el swagger al cual se puede acceder cuando la aplicacion se este ejecutando por el
 * url: http://localhost:8080/swagger-ui.html#/
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * Configuracion de bean para el swagger
     */
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.posinhodev.ToDoApi.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    /**
     * Informacion que va a ser mostrada en el swagger-ui a los usuarios
     * que utilicen el api
     */
    private ApiInfo apiInfo(){
        return new ApiInfo(
                "ToDo Api Rest",
                "La API REST de ToDo app",
                "V1",
                "Terms of service",
                new Contact("posinhodev", "https://github.com/posinhodev", "jose.possop12@gmail.com"),
                "Licence of API", "API License URL", Collections.emptyList()
        );
    }
}
