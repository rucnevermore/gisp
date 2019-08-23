package com.haizhi.geoserver.swagger

import io.swagger.annotations.ApiOperation
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
@author: teddy liu
@date: 2019-08-23 20:21
 */

//http://localhost:8080/swagger-ui.html#/
@EnableSwagger2
@Configuration
open class SwaggerConfiguration {
    @Bean
    open fun createRestApi(): Docket {
        return  Docket(DocumentationType.SWAGGER_2).select()
            .apis(
                RequestHandlerSelectors
                .withMethodAnnotation(ApiOperation::class.java)).build();
    }

}