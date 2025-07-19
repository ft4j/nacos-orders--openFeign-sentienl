package com.tuling.springcloud.stock.设计模式.结构模式.桥接模式;

import lombok.Data;

@Data
public class Bradge {
    FileType fileType;
    Operator operator;
    public Bradge(FileType fileType,Operator operator){
        this.fileType = fileType;
        this.operator = operator;
    }
    public Bradge(){

    }


    public void showFileInOperator(){
        operator.showImg(fileType);
    }
}
