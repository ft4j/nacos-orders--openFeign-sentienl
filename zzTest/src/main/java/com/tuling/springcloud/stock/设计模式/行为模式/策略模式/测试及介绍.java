package com.tuling.springcloud.stock.设计模式.行为模式.策略模式;

import org.junit.Test;

/**
 * 策略模式，本次的案例为电影票打折案例
 * 有好几种折扣的存在，在策略模式下，本案例中存在多种策略，此时每一种策略都被封装为一个类
 *
 * 策略模式就是根据接口的实现类的不同，调用不同的实现，非常简单的一个模式
 *
 */
public class 测试及介绍 {
    @Test
    public void dddd(){
        MovieTicket mt = new MovieTicket();
        mt.setPrice(60);
        mt.setDiscount(new StudentDiscount());
        double movieTicketPrince = mt.getMovieTicketPrince();
        System.out.println(movieTicketPrince);
    }
}
