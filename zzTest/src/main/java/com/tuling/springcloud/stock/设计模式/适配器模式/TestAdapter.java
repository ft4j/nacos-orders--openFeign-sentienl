package com.tuling.springcloud.stock.设计模式.适配器模式;

import org.junit.Test;

//适配器模式功能是给一个原先功能单一的服务，通过一个适配器添加功能
public class TestAdapter {
    @Test
    public void cc(){
        MediaPlayer mediaPlayer = new AudioPlayer();
        mediaPlayer.play("mp3","七里香");
        mediaPlayer.play("mp3","苍老师");
        mediaPlayer.play("vlc","不知所谓");
        mediaPlayer.play("dd","dff");
    }

}
