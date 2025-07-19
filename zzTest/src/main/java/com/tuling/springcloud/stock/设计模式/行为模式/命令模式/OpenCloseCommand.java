package com.tuling.springcloud.stock.设计模式.行为模式.命令模式;

public class OpenCloseCommand implements Command {
    private Device device;
    public OpenCloseCommand(Device device){
        this.device = device;
    }
    @Override
    public void execute() {
        device.on();
    }

    @Override
    public void undo() {
        device.off();
    }
}
