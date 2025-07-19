package com.example.aop.aop.dto.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.aop.aop.dto.entity.Deletetesttable;
import com.example.aop.aop.dto.mapper.DeletetesttableMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DeletetesttableRepositoryImpl
        extends ServiceImpl<DeletetesttableMapper, Deletetesttable>
        implements DeletetesttableRepository {

}
