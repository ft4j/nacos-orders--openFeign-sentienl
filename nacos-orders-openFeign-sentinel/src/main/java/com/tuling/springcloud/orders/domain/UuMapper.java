package com.tuling.springcloud.orders.domain;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface UuMapper {
    Uu query1(@Param("name") String name);
    Map queryForMap(@Param("name") String name);
    ResultMapBean queryForResult(@Param("name") String name);

    List<ResultMapBean> queryForResult11(@Param("name") String name);

    List<Uu> query11(@Param("name") String name);

    List<ResultMapBean> queryForObject(@Param("user") User user);
}
