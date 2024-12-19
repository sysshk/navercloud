package com.smhrd.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Class 파일에서 설정을 하는 경우도 있음
// 외부 리소스 접근 url을 만드는 설정, security 등..

@Configuration
public class ResourceConfig implements WebMvcConfigurer {

	@Value("${save.path}")
	private String savePath;
	
	// 외부 리소스(폴더)에 접근하는 URL을 지정하는 것
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		
		// http://localhost:8088/save/**
		// ** > 전체.전체확장자
		registry.addResourceHandler("/save/**") // 어떤 양식의 URL인지
			    .addResourceLocations( "file:///" + savePath); // 어떤 폴더를 연결할 건지
		// file:///  <- 파일통신 프로토콜이 붙어있어야 함.
		// 세미콜론 주의해야함. 2줄이상이 되니까
	}
	
	// bean : 스프링이 생성하고 관리하는 객체 
	// 인터페이스 + xml로 DAO를 구현해줌
	
}
