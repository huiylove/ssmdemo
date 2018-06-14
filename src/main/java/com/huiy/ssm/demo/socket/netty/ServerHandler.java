package com.huiy.ssm.demo.socket.netty;
import io.netty.buffer.ByteBuf;  
import io.netty.buffer.Unpooled;  
import io.netty.channel.ChannelHandlerContext;  
import io.netty.channel.ChannelInboundHandlerAdapter;  
import java.io.UnsupportedEncodingException;  


/**
 * 处理器是由Netty生成用来处理I/O事件的
 * @author user
 *
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {  
	
	 
	/** 
     * 收到客户端消息后调用 
     * @throws UnsupportedEncodingException  
     */  
    @Override  
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {  
    	ByteBuf in = (ByteBuf) msg;  
        byte[] req = new byte[in.readableBytes()];  
        in.readBytes(req);  
        String body = new String(req,"utf-8");  
        System.out.println("收到客户端消息:"+body);  
        String calrResult = null;  
        try{  
            calrResult = Calculator.Instance.cal(body).toString();  
        }catch(Exception e){  
            calrResult = "错误的表达式：" + e.getMessage();  
        }  
        ctx.write(Unpooled.copiedBuffer(calrResult.getBytes()));  
    }  
    
    @Override  
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {  
        ctx.flush();  
    }  
    
    /** 
     * 异常处理 
     * exceptionCaught()事件处理方法是当出现Throwable对象才会被调用，
     * 即当Netty由于IO错误或者处理器在处理事件时抛出的异常时
     */  
    @Override  
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {  
        cause.printStackTrace();  
        ctx.close();  
    }  
}  
