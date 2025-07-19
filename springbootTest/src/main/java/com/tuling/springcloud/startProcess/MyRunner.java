package com.tuling.springcloud.startProcess;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;

public class MyRunner implements ApplicationRunner, CommandLineRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("applicationRunner run........");
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner run........");
    }
}
