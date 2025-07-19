package com.tuling.springcloud.stock.IO.BIO;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    @Test
    public void cccc() throws IOException {
        String serverAddress = "127.0.0.1";
        int port = 8256;
        Socket socket = new Socket(serverAddress,port);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        //发送请求
        String requestBody = "hello server!";
        writer.println(requestBody);
        writer.flush();
        System.out.println("发送请求给服务器：" + requestBody);

        //接受响应
        String response = reader.readLine();
        System.out.println("接收到消息："+response);
        socket.close();

        System.out.println("请求结束");

    }
}
