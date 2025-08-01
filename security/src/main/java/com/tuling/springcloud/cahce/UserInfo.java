package com.tuling.springcloud.cahce;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_info")
public class UserInfo {
    @TableId
    private Long id;
    private String userName;
    private String password;
    private String customerName;
    @TableField(exist = false)
    private List<String> roles;
}
