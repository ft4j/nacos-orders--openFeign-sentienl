package com.tuling.springcloud.orders.乱写包;


public class IOCTestt {

    public void pri(){
        System.out.println("iocTest功能");
    }
    public IOCTestt getIocTest(){
        return new IOCTestt();
    }

    public void ddddx(IOCTesttt iocTest){
        iocTest.dddd("hhhh");
    }
}
