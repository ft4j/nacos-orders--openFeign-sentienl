package com.tuling.springcloud.stock.spring.transaction.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuling.springcloud.stock.spring.transaction.entity.Deletetesttable;
import com.tuling.springcloud.stock.spring.transaction.mapper.DeletetesttableMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DeletetesttableRepositoryImpl
        extends ServiceImpl<DeletetesttableMapper, Deletetesttable>
        implements DeletetesttableRepository {

}
