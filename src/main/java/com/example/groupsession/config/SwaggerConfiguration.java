package com.example.groupsession.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	public static final String AUTHORIZATION_HEADER = "Authorization";
	
	private ApiInfo apiInfo() {
		return new ApiInfo(
				"David's REST API",
				"For a Magenic Masters course",
				"1.0.0.0",
				"All rights reserved",
				new Contact("David P. Donahue", "", "davidd@magenic.com"),
				"MIT License",
				"Wherever the MIT license is found",
				Collections.emptyList()
			);
	}
	
	@Bean
	public Docket api() {
		List<SecurityContext> contexts = new ArrayList<SecurityContext>();
		contexts.add(this.securityContext());
		List<SecurityScheme> keys = new ArrayList<SecurityScheme>();
		keys.add(this.apiKey());
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.securityContexts(contexts)
				.securitySchemes(keys)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}
	
	private ApiKey apiKey() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}
	
	private SecurityContext securityContext() {
		return SecurityContext.builder()
				.securityReferences(defaultAuth())
				.build();
	}
	
	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		List<SecurityReference> references = new ArrayList<SecurityReference>();
		references.add(new SecurityReference("JWT", authorizationScopes));
		return references;
	}
}
