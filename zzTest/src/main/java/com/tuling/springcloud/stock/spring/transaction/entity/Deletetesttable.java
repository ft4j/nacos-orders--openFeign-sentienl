package com.tuling.springcloud.stock.spring.transaction.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("deletetesttable")
public class Deletetesttable {
    @TableId("id")
    private Integer id;
    @TableField("name")
    private String name;
}
