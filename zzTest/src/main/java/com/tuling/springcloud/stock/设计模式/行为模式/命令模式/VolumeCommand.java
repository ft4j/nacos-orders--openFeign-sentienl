package com.tuling.springcloud.stock.设计模式.行为模式.命令模式;

public class VolumeCommand implements Command{
    private Device device;
    public VolumeCommand(Device device){
        this.device = device;
    }
    @Override
    public void execute() {
        device.volumeUp();
    }

    @Override
    public void undo() {
        device.volumeDown();
    }
}
