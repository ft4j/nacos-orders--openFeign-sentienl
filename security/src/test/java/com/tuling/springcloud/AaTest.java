package com.tuling.springcloud;

import com.tuling.springcloud.util.JwtUtil;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AaTest {
    @Test
    public void sfsdf() throws InterruptedException {
        String s = JwtUtil.generateToken("myNamezzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        System.out.println(s);
        Thread.sleep(1000);
        String subject = JwtUtil.getSubject(s);
        System.out.println(subject);
    }

    @Test
    public void ffddss(){
        PasswordEncoder pe = new BCryptPasswordEncoder(12);
        String encode = pe.encode("123456");
        System.out.println(encode);
    }

    @Test
    public void dsdfsdf(){
        PasswordEncoder pe = new BCryptPasswordEncoder(12);
        boolean matches = pe.matches("123456", "$2a$12$JV8ecOjkmpR49w19rN.YdeCnAZr.gsTno8oI3QVUu7IjlxdkkKNb2");
        System.out.println(matches);
    }
}
