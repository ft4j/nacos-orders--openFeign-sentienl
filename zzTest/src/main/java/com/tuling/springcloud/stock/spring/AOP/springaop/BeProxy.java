package com.tuling.springcloud.stock.spring.AOP.springaop;

public class BeProxy {

    private String card;
    private String cardType;

    public void init_method(){
        System.out.println("正在init");
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
}
