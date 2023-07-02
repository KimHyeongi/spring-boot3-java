package com.tistory.eclipse4j.app.api.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.resource.PathResourceResolver;


@Configuration
public class ApiWebConfiguration extends WebMvcConfigurationSupport {

	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		registry.addResourceHandler("/docs/**")
				.addResourceLocations("classpath:/public/", "classpath:/static/", "classpath:/resources/", "classpath:/META-INF/resources/")
				.resourceChain(true)
				.addResolver(new PathResourceResolver());
	}
}
