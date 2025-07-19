package com.tuling.springcloud.stock.设计模式.结构模式.桥接模式;

import org.junit.Test;

import javax.xml.crypto.dsig.keyinfo.PGPData;

/**
 * 桥接模式
 * 假设有一个场景，需要在不同的操作系统下展示图片
 * 如：在linux和windows下展示，jpg和png格式的图片
 * 如果不考虑设计模式，此时需要2个展示接口和2个图片接口，同时需要4个方法去一一组合操作系统和格式
 * 此时，如果有10种图片格式，就得写20个方法去适配
 *
 * 此时应该考虑桥接模式
 * 将操作系统实现抽象化，将格式抽象化
 * 这里的Bradge就是桥接的实现
 */
public class 介绍及测试 {
    @Test
    public void bri(){
        Bradge bradge = new Bradge();
        bradge.setFileType(new Png());
        bradge.setOperator(new Linux());
        bradge.showFileInOperator();
    }
}
