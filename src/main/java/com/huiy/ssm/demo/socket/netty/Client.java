package com.huiy.ssm.demo.socket.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

/** 
 * 类功能描述
 * @author : yuanhui 
 * @date   : 2018年4月25日
 * @version 1.0
 */
public class Client implements Runnable{  
	
    static ClientHandler clientHandler = new ClientHandler();  
    
    public static void main(String[] args) throws Exception {  
        new Thread(new Client()).start();  
        @SuppressWarnings("resource")  
        Scanner scanner = new Scanner(System.in);  
        while(clientHandler.sendMsg(scanner.nextLine()));  
    }  
    
    @Override  
    public void run() {  
        String host = "127.0.0.1";  
        int port = 9090;  
        EventLoopGroup workerGroup = new NioEventLoopGroup();  
        try {  
            Bootstrap b = new Bootstrap();  
            b.group(workerGroup);  
            b.channel(NioSocketChannel.class);  
            b.option(ChannelOption.SO_KEEPALIVE, true);  
            b.handler(new ChannelInitializer<SocketChannel>() {  
                @Override  
                public void initChannel(SocketChannel ch) throws Exception {  
                    ch.pipeline().addLast(clientHandler);  
                }  
            });  
            ChannelFuture f = b.connect(host, port).sync();  
            System.out.println("客户端开启："+port);  
            f.channel().closeFuture().sync();  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        } finally {  
            workerGroup.shutdownGracefully();  
        }  
    }  
}  