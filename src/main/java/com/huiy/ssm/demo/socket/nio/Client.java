package com.huiy.ssm.demo.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/** 
 * 类功能描述
 * @author : yuanhui 
 * @date   : 2018年4月13日
 * @version 1.0
 */
public class Client {

	public static void main(String[] args) {
		InetSocketAddress address = new InetSocketAddress("127.0.0.1", 7788);//创建连接的地址
        SocketChannel sc = null;//声明连接通道
        try {
			ByteBuffer writeBuf = ByteBuffer.allocate(1024);//建立缓冲区
//			ByteBuffer readBuf = ByteBuffer.allocate(1024);//建立缓冲区
		    sc = SocketChannel.open();//打开通道
		    sc.connect(address);//进行连接
		    while(true){
//		    	 	//接受服务器端的消息
//		    	   int count = sc.read(readBuf);
//		           //4 如果没有数据
//		           if(count == -1){
//		        	    sc.close();
//		                return;
//		            }
//		            //5 有数据则进行读取 读取之前需要进行复位方法(把position 和limit进行复位)
//		            readBuf.flip();
//		            //6 根据缓冲区的数据长度创建相应大小的byte数组，接收缓冲区的数据
//		            byte[] readBytes = new byte[readBuf.remaining()];
//		            //7 接收缓冲区数据
//		            readBuf.get(readBytes);
//		            //8 打印结果
//		            String body = new String(readBytes).trim();
//		            System.out.println("Client : " + body);
		            
			        //定义一个字节数组，然后使用系统录入功能：
			        byte[] bytes = new byte[1024];
			        System.in.read(bytes);
			        writeBuf.put(bytes);//把数据放到缓冲区中
			        writeBuf.flip();//对缓冲区进行复位
			        sc.write(writeBuf);//写出数据
			        writeBuf.clear();//清空缓冲区数据
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
