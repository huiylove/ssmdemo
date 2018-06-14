package com.huiy.ssm.demo.socket.bio.jdk14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/** 
 * 类功能描述
 * @author : yuanhui 
 * @date   : 2018年4月13日
 * @version 1.0
 */
public class ServerHandler implements Runnable{

    private Socket socket ;

    public ServerHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
        	
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(), true);
            String body = null;
            while(true){
                body = in.readLine();
                if(body == null) break;
                System.out.println("Server :" + body);
                out.println("hello,yuanhui!!!");
            }
        }catch(Exception e){
        	e.printStackTrace();
        }
    }
}