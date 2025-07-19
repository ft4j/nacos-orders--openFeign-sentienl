package com.tuling.springcloud.stock.设计模式.行为模式.命令模式;

import org.junit.Test;

/**
 * 电灯只有两个动作：    开和关  OpenClose
 * 风扇有四个动作：      开、关、加挡和减挡  OpenClose+ChannelSwitch
 * 电视有六个动作：      开、关、加台、减台、加音量和减音量  OpenClose+ChannelSwitch+Device
 *
 * 命令模式中有4个角色  分别是 1、调用者（SwitchInvoke）  2、命令接口（Command）
 *                        3、具体的命令实现（OpenCloseCommand等） 4、接收者（DeviceImpl，它其实才是最终实现功能的代码）
 * 其中调用者有1个，命令接口1个，具体的命令实现有多个，接收者有多个
 */
public class 测试和介绍 {

    @Test
    public void minglingceshi(){
        System.out.println("开始操作电灯======");
        Device de = new OpenCloseImpl();
        Command cm = new OpenCloseCommand(de);
        SwitchInvoke si = new SwitchInvoke();
        si.bindKaiguanCommand(cm);
        //此时开关键是起作用的
        si.buttonOnClick();
        si.buttonOffClick();
        System.out.println("开始操作电视机音量大小=============");
        Device dee = new DeviceImpl();
        Command cmm = new ChannelCommand(dee);
        Command cm1 = new ChannelCommand(dee);
        Command cm2 = new VolumeCommand(dee);

        SwitchInvoke si1 = new SwitchInvoke();
        //是的，三个都得绑
        si1.bindKaiguanCommand(cmm);
        si1.bindShangxiaCommand(cm1);
        si1.bindZuoyouCommand(cm2);
        
        si1.buttonLeftClick();
        si1.buttonRightClick();
        si1.buttonUpClick();
        si1.buttonDownClick();
        si1.buttonOnClick();
        si1.buttonOffClick();

//        Device device = new VolumeCommand();
    }
}
