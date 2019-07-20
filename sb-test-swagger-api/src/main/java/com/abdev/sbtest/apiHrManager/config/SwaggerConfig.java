package com.abdev.sbtest.apiHrManager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.AuthorizationCodeGrantBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
/*
import java.util.Arrays;
import java.util.List;

import static org.keycloak.OAuth2Constants.CLIENT_ID;
import static org.keycloak.OAuth2Constants.CLIENT_SECRET;*/
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket testApi(){

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.abdev.sbtest.apiHrManager.controller"))
                .paths(regex("/api.*"))
                .build()
                .apiInfo(metaInfo());
    }

   /* @Bean
    public SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder()
                .clientId(CLIENT_ID)
                .clientSecret(CLIENT_SECRET)
                .scopeSeparator(" ")
                .useBasicAuthenticationWithAccessCodeGrant(true)
                .build();
    }*/

    private ApiInfo metaInfo(){
        ApiInfo apiInfo =   new ApiInfo(
                "TEST API REST",
                "API REST pour la Gestion d'un Entreprise",
                "1.0",
                "Terms of Service",
                new Contact("Ababacar Diaw","https://www.vip-glam.com.com","diaw.ababacar94@gmail.com"),
                "Aucune Licence",
                "Aucune Licence",
                new ArrayList<VendorExtension>()
        );
        return apiInfo;
    }
}
