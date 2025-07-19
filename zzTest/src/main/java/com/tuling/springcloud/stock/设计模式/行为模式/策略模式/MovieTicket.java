package com.tuling.springcloud.stock.设计模式.行为模式.策略模式;

import lombok.Data;

@Data
public class MovieTicket {
    //电影票原价
    private double price;
    //折扣类型
    private Discount discount;

    public double getMovieTicketPrince(){
        return discount.calculate(price);
    }
}
