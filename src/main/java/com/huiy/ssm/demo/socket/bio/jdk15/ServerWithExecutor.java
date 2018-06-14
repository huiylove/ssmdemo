package com.huiy.ssm.demo.socket.bio.jdk15;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.huiy.ssm.demo.socket.bio.jdk14.ServerHandler;

/** 
 * 类功能描述
 * @author : yuanhui 
 * @date   : 2018年4月13日
 * @version 1.0
 */
public class ServerWithExecutor {
	final static int port = 7788;
	public static void main(String[] args) {
		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
			 //进行阻塞
			 System.out.println("server start");
            Socket socket = null;
            HandlerExecutorPool executorPool = new HandlerExecutorPool(50, 1000);
            while(true){
                socket = server.accept();
                executorPool.execute(new ServerHandler(socket));
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
