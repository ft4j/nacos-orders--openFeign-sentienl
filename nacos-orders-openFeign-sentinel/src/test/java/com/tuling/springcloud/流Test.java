package com.tuling.springcloud;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;

public class 流Test {
    @Test
    public void ddd() throws FileNotFoundException, UnsupportedEncodingException {
        File file = new File("");
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        byte[] ds = new String().getBytes("d");
        ByteBuffer d = ByteBuffer.allocateDirect(1024);
        d.put(ds);
        d.flip();
    }
}
