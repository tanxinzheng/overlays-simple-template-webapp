package com.xmomen.module.core.web;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * Created by tanxinzheng on 16/12/23.
 */
@EnableSwagger
public class ApplicationSwaggerConfig {

    private SpringSwaggerConfig springSwaggerConfig;

    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;
    }

    @Bean //Don't forget the @Bean annotation
    public SwaggerSpringMvcPlugin customImplementation(){
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "My Apps API Title",
                "My Apps API Description",
                "My Apps API terms of service",
                "My Apps API Contact Email",
                "My Apps API Licence Type",
                "My Apps API License URL"
        );
        return apiInfo;
    }
}
