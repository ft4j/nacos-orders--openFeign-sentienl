package com.tuling.springcloud.stock.设计模式.适配器模式自测;

public class RadioAdapter {
    Radio radio;

    public RadioAdapter(String type){
        if("Time".equals(type)){
            radio = new Time();
        }else if("Play".equals(type)){
            radio = new Play();
        }
    }

    public void adapterMethod(String type){
        if("Time".equals(type)){
            radio.time();
        }else if("Play".equals(type)){
            radio.play();
        }
    }

}
