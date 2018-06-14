package com.huiy.ssm.demo.socket.netty;
import io.netty.bootstrap.ServerBootstrap;  
import io.netty.channel.ChannelFuture;  
import io.netty.channel.ChannelInitializer;  
import io.netty.channel.ChannelOption;  
import io.netty.channel.EventLoopGroup;  
import io.netty.channel.nio.NioEventLoopGroup;  
import io.netty.channel.socket.SocketChannel;  
import io.netty.channel.socket.nio.NioServerSocketChannel;  


/** 
 * 类功能描述
 * @author : yuanhui 
 * @date   : 2018年4月19日
 * @version 1.0
 */
public class Server {  
    private int port;  
    
    public Server(int port) {  
        this.port = port;  
    }  
    
    public void run() throws Exception {  
        EventLoopGroup bossGroup = new NioEventLoopGroup();  //EventLoopGroup处理I/O操作的多线程事件循环器
        EventLoopGroup workerGroup = new NioEventLoopGroup();  
        try {  
            ServerBootstrap b = new ServerBootstrap();  
            b.group(bossGroup, workerGroup)  
             .channel(NioServerSocketChannel.class)  
             .option(ChannelOption.SO_BACKLOG, 1024)  
             .childOption(ChannelOption.SO_KEEPALIVE, true)  
             .childHandler(new ChannelInitializer<SocketChannel>() {  
                 @Override  
                 public void initChannel(SocketChannel ch) throws Exception {  
                     ch.pipeline().addLast(new ServerHandler());  
                 }  
             });  
            ChannelFuture f = b.bind(port).sync();  
            System.out.println("服务器开启："+port);  
            f.channel().closeFuture().sync();  
        } finally {
            workerGroup.shutdownGracefully();  
            bossGroup.shutdownGracefully();  
        }  
    }  
    
    public static void main(String[] args) throws Exception {  
        int port;  
        if (args.length > 0) {  
            port = Integer.parseInt(args[0]);  
        } else {  
            port = 9090;  
        }  
        new Server(port).run();  
    }  
}  
