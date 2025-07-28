package com.tuling.springcloud.cahce;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 记忆RBAC的权限控制体系----基于角色的权限模型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_role")
public class UserRoleInfo {
    @TableId
    private Long id;
    private String userName;
    private String password;
    private String customerName;
}
