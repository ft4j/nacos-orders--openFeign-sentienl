package com.tuling.springcloud.stock.设计模式.行为模式.命令模式;

public class ChannelCommand implements Command {
    private Device device;
    public ChannelCommand(Device device){
        this.device = device;
    }
    @Override
    public void execute() {
        device.channelUp();
    }

    @Override
    public void undo() {
        device.channelDown();
    }
}
