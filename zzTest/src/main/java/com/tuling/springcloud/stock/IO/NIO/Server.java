package com.tuling.springcloud.stock.IO.NIO;

import org.junit.Test;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class Server {
    @Test
    public void dsdsdggggskjfs(){
        ByteBuffer bb = ByteBuffer.allocate(100);
        System.out.println(bb.capacity()+":::"+ bb.limit()+":::"+bb.position());
        bb.position(20);
        System.out.println(bb.capacity()+":::"+ bb.limit()+":::"+bb.position());
        bb.flip();
        System.out.println(bb.capacity()+":::"+ bb.limit()+":::"+bb.position());

        ByteBuffer bbb = ByteBuffer.allocate(100);
        bbb.put("123456789".getBytes());
        bbb.flip();
        System.out.println(bbb.hasRemaining());
        System.out.println(bbb.remaining());

    }
}
