package com.tuling.springcloud.stock.IO.BIO;

import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    @Test
    public void ddd() throws IOException {
        int port = 8256;
        ServerSocket ss = new ServerSocket(port);
        while(true){
            Socket socket = ss.accept();
            System.out.println("客户端连接成功，地址"+socket.getInetAddress()+":"+socket.getPort());
            new Thread(()->{
                try {
                    InputStream inputStream = socket.getInputStream();
                    InputStreamReader isr = new InputStreamReader(inputStream);
                    BufferedReader reader = new BufferedReader(isr);

                    PrintWriter writer = new PrintWriter(socket.getOutputStream());
                    String s = reader.readLine();

                    System.out.println("服务器接收到："+s);
                    String response = "hello client";
                    writer.println(response);

                    writer.flush();
                    System.out.println("发送响应给客户端：" + response);
                    socket.close();
                    System.out.println("客户端连接关闭");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
