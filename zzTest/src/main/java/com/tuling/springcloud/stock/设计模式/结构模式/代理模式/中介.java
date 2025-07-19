package com.tuling.springcloud.stock.设计模式.结构模式.代理模式;

public class 中介 implements 房地产{
    房地产 f;
    public 中介(房地产 k){
        this.f = k;
    }
    private void 带看房Pre(){
        System.out.println("中介带客户看房");
    }
    @Override
    public void 买房子() {
        带看房Pre();
        f.买房子();
        办证Post();
    }

    private void 办证Post(){
        System.out.println("中介帮忙办房产证");
    }
}
