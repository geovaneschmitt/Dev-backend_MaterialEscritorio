package com.geoschmitt.Devbackend_MaterialEscritorio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class DevBackendMaterialEscritorioApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevBackendMaterialEscritorioApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean corsFilter() {
		
		
		
		CorsConfiguration cf = new CorsConfiguration();
		cf.setAllowCredentials(true);
		cf.addAllowedMethod("*");
		cf.addAllowedOrigin("*");
		cf.addAllowedHeader("*");
		
		UrlBasedCorsConfigurationSource src = new UrlBasedCorsConfigurationSource();
		src.registerCorsConfiguration("/**", cf);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(src));
		bean.setOrder(-100000);
		return bean;
	}

}
