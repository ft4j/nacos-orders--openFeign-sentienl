package com.tuling.springcloud.stock.设计模式.行为模式.命令模式;

public class SwitchInvoke {
    private Command command;
    private Command kaiguanCommand;
    private Command shangxiaCommand;
    private Command zuoyouCommand;

    public void bindKaiguanCommand(Command kaiguanCommand){
        this.kaiguanCommand = kaiguanCommand;
    }
    public void bindShangxiaCommand(Command shangxiaCommand){
        this.shangxiaCommand = shangxiaCommand;
    }
    public void bindZuoyouCommand(Command zuoyouCommand){
        this.zuoyouCommand = zuoyouCommand;
    }

    public void buttonOnClick() {
        System.out.println("按下开机键");
        kaiguanCommand.execute();
    }
    public void buttonOffClick() {
        System.out.println("按下关机键");
        kaiguanCommand.undo();
    }
    public void buttonUpClick() {
        System.out.print("按下↑按键……");
        shangxiaCommand.execute();
    }
    public void buttonDownClick() {
        System.out.print("按下↓按键……");
        shangxiaCommand.undo();
    }
    public void buttonLeftClick() {
        System.out.print("按下←按键……");
        zuoyouCommand.undo();
    }
    public void buttonRightClick() {
        System.out.print("单击→按键……");
        zuoyouCommand.execute();
    }

}
