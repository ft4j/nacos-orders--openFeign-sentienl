package com.example.sentineldemo.model;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String id;
    private String name;
    //这是设置在本类之外的留空降级处理方法
//    public static User blockHandlerFOrUser(String id, BlockException exception){
//        return new User(id,"被流控了！！");
//    }
}
