package com.api;

import java.text.SimpleDateFormat;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.api.rep.filter.DebugFilter;
import com.api.rep.filter.TokenFilter;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class RepApiApplication {

	// filtro para debug,
	@Bean
	public FilterRegistrationBean filtroDebug() {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new DebugFilter());
		bean.addUrlPatterns("/*");
		return bean;
	}

	// filtro para as requisicoes restritas
	@Bean
	public FilterRegistrationBean filtroJwt() {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new TokenFilter());
		bean.addUrlPatterns("/restrict/*");

		return bean;
	}

	// configurar o mapper para não enviar \r\n no json
	@Bean
	public Jackson2ObjectMapperBuilder jacksonBuilder() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
		Jackson2ObjectMapperBuilder b = new Jackson2ObjectMapperBuilder();
		b.indentOutput(false).dateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		b.failOnUnknownProperties(false);
		b.configure(mapper);
		return b;
	}

	@Bean
	MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("1280KB");
		factory.setMaxRequestSize("1280KB");
		factory.setLocation(System.getProperty("java.io.tmpdir"));
		return factory.createMultipartConfig();
	}

	// Start inicial do app
	public static void main(String[] args) {
		SpringApplication.run(RepApiApplication.class, args);
	}
}
