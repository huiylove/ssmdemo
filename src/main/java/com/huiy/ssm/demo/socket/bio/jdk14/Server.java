package com.huiy.ssm.demo.socket.bio.jdk14;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/** 
 * 类功能描述
 * @author : yuanhui 
 * @date   : 2018年4月13日
 * @version 1.0
 * ServerSocket负责绑定IP地址，启动监听端口；Socket负责发起连接操作
 */
public class Server {
	final static int port = 7788;
	public static void main(String[] args) {
		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
			 //进行阻塞
            Socket socket = server.accept();
            new Thread(new ServerHandler(socket)).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
