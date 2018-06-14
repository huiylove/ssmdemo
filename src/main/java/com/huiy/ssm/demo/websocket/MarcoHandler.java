package com.huiy.ssm.demo.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

/** 
 * 类功能描述
 * @author : yuanhui 
 * @date   : 2017年8月2日
 * @version 1.0
 *
 *
 */
public class MarcoHandler extends AbstractWebSocketHandler{
	private  Logger logger = LoggerFactory.getLogger(MarcoHandler.class);
	
	protected void handlerTextMessage(WebSocketSession session,TextMessage message) throws Exception{
		logger.info("Received message:"+message.getPayload());
		Thread.sleep(2000);
		session.sendMessage(new TextMessage("Polo!!!"));
	}
	
	public void afterConnectionEstablished(WebSocketSession session){
		logger.info("Connection Established");
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session,CloseStatus status){
		logger.info("Connection closed. Status:"+status);
	}
}
