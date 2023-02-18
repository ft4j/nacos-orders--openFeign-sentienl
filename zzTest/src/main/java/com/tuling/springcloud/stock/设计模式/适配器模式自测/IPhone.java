package com.tuling.springcloud.stock.设计模式.适配器模式自测;
//我们要让这个电话在使用的时候具有打电话、播放和时间功能
public class IPhone implements Phone{
    RadioAdapter radioAdapter;
    @Override
    public void use(String type) {

        if("Time".equals(type) || "Play".equals(type)){
            radioAdapter = new RadioAdapter(type);
            radioAdapter.adapterMethod(type);
        }else{
            System.out.println("我打了个电话，内容是："+type);
        }

    }
}
