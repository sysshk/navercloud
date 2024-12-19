package com.smhrd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import lombok.RequiredArgsConstructor;


// 생성자 : 클래스와 동일한이름을 가진 메소드, 안에 선언되어있는 필드에 값을 집어넣어주는 역할을 함.


@EnableWebSocket // 웹소켓을 사용 시작할 것이다
@RequiredArgsConstructor
@Configuration
public class WebSocketConfig implements WebSocketConfigurer {
	
	// 상수에는 값을 채워줘야 함.
	private final WebSocketHandler handler;
	
	// alt + shift + s 해서 오버라이드할수 있음.
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		
		// 웹소켓 핸들러와 url을 연결하고자 함.
		// ws://localhost:8088/chat
		registry.addHandler(handler, "/chat/{room}")
				.setAllowedOrigins("*");
				// 동일출처 정책을 해소하기 위함.
				// 외부 서부에서 연결을 허용
				
		// CORS ? A만요청하면 A만 받음 
		
	}

	
	
	
}
