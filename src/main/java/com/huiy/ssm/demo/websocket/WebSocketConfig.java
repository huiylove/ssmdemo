package com.huiy.ssm.demo.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/** 
 * 类功能描述
 * @author : yuanhui 
 * @date   : 2017年8月2日
 * @version 1.0
 *
 *
 */
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addHandler(marcoHandler(), "/marco");
	}
	
	@Bean
	public MarcoHandler marcoHandler(){
		return new MarcoHandler();
	}
	
	
}
