package com.hugolopes.searchmodule.config.swagger;

import java.util.Arrays;

import com.hugolopes.searchmodule.model.Station;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfigurations {

    @Bean
    public Docket SearchModuleApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hugolopes.searchmodule"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .ignoredParameterTypes(Station.class)
                .globalOperationParameters(Arrays.asList(
                        new ParameterBuilder()
                                .name("Search Module")
                                .description("Implementa Hibernate Search com Apache Lucene para realizar a busca de estações.")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .required(false)
                                .build()));
    }

}
