package com.huiy.ssm.demo.socket.bio.jdk14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/** 
 * 类功能描述
 * @author : yuanhui 
 * @date   : 2018年4月13日
 * @version 1.0
 */
public class Client {
	final static String ip_address = "127.0.0.1";
	final static int port = 7788;
	
	public static void main(String[] args) {
		Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
			socket = new Socket(ip_address, port);
			in = new  BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
			out.println("hello world to server");
			System.out.println("client: "+in.readLine());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
}
