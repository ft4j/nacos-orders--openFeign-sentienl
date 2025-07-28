package com.tuling.springcloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tuling.springcloud.cahce.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    public List<String> queryPermission(@Param("userName")String userName);
}
