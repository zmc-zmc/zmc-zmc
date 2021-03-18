package com.zmc.parentmodule.swaggerImp;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.zmc.parentmodule.controller")).paths(PathSelectors.any())
				.build().apiInfo(getApiInfo());
	}
	
	public ApiInfo getApiInfo() {
        return new ApiInfo("Modules API",
                "Entire Modules API",
                "1.0",
                "https://zmc.com",
                new Contact("Rahim Sheik", "https://zmc.com", "admin_desk@zmc.com"),
                "Terms of Use Licence",
                "https://zmc.com",
                Collections.emptyList()
        );
    }
}
