package com.tuling.springcloud.stock.spring.transaction;

import com.tuling.springcloud.stock.spring.transaction.entity.Deletetesttable;
import com.tuling.springcloud.stock.spring.transaction.mapper.DeletetesttableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DataService {



    @Autowired
    DeletetesttableMapper mapper;

    public Deletetesttable getEntity(){
        Deletetesttable deletetesttable = mapper.selectById(7);
        return deletetesttable;
    }

    @Transactional(propagation= Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED
    ,rollbackFor = {Fe.class,Ee.class})
    public void addDeletetesttable(){
        Deletetesttable d = new Deletetesttable();
        d.setId(1000);
        d.setName("张三");
        mapper.insert(d);
        System.out.println(1/0);
    }
    @Autowired
    DataService dataService;
    public void textTransaction(){
        dataService.insertMethod1();
    }

    @Transactional
    public void insertMethod1(){
        Deletetesttable d = new Deletetesttable();
        d.setId(1);
        d.setName("张三");
        mapper.insert(d);
        dataService.insertMethod2();
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertMethod2(){
        Deletetesttable d = new Deletetesttable();
        d.setId(2);
        d.setName("李四");
        mapper.insert(d);
//        throw new RuntimeException();
    }

    @Transactional
    public void insertMethod3(){
        Deletetesttable d = new Deletetesttable();
        d.setId(1);
        d.setName("张三");
        mapper.insert(d);
        try{
            dataService.insertMethod4();
        }catch(Exception e){
            e.printStackTrace();
        }

        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.NESTED)
    public void insertMethod4(){
        Deletetesttable d = new Deletetesttable();
        d.setId(2);
        d.setName("李四");
        mapper.insert(d);
//        throw new RuntimeException();
    }

    @Transactional
    public void insertMethod5(){
        Deletetesttable d = new Deletetesttable();
        d.setId(1);
        d.setName("张三");
        mapper.insert(d);
        try{
            dataService.insertMethod6();
        }catch(Exception e){
            e.printStackTrace();
        }

//        throw new RuntimeException();
    }

    @Transactional
    public void insertMethod6(){
        Deletetesttable d = new Deletetesttable();
        d.setId(2);
        d.setName("李四");
        mapper.insert(d);
        throw new RuntimeException();
    }


    /**
     * @Transactional源码
     * REQUIRED_NEW，它永远会新建一个事务，但是外部事务报错必然出现在内部事务提交之后，参考insertMethod1()
     * 所以内部事务提交与否肯定收到内部影响，但是外部事务报错时，内部已经提交，所以外部不影响内部
     * NESTED这种嵌套方式，其实是声明式事务设置savePoint的方法如果想起到内部不影响外部，外部影响内部
     * 那么内部必须内try-catch，否则内部也向外抛异常，外部必然失败
     * 参考insertMethod3，如果不加try-catch，nested结果和required一样了
     * REQUIRED则是，只要异常被try-catch，那么全部成功，没有try-catch就一起成功或者一起失败，参考insertMethod6和insertMethod5
     * 如果method6已经抛出异常，在method5捕获，那么事务也是全部失败，因为已经走过了一次@Transactional修饰的展示，rollback已经发生
     * 想要REQUIRED发生异常还能不回滚，需要在方法中就try-catch住异常，不让他跑到@Transactional中
     */

}
