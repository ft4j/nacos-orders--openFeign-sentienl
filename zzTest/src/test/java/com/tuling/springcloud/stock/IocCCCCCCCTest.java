package com.tuling.springcloud.stock;

import com.tuling.springcloud.digest.Digest;
import com.tuling.springcloud.stock.config.BeanBean;
import com.tuling.springcloud.stock.config.ConfigFalse;
import com.tuling.springcloud.stock.config.ConfigTrue;
import com.tuling.springcloud.stock.spring.AOP.AopDemo.AopDemoService;
import com.tuling.springcloud.stock.spring.AOP.AopDemo.AspecjDemo;
import com.tuling.springcloud.stock.spring.AOP.cglib.ProxyClass;
import com.tuling.springcloud.stock.spring.AOP.cglib.beEnhancedClass;
import com.tuling.springcloud.stock.spring.ioc.FactoryBeanImp;
import com.tuling.springcloud.stock.spring.ioc.FactoryBeanStudent;
import com.tuling.springcloud.stock.spring.ioc.bean.MyAwareBean;
import com.tuling.springcloud.stock.spring.ioc.bean.扩展bean;
import com.tuling.springcloud.stock.spring.ioc.zz.Zzz;
import com.tuling.springcloud.stock.spring.ioc.zz.Zzzz;
import com.tuling.springcloud.stock.spring.transaction.DataService;
import com.tuling.springcloud.stock.spring.transaction.entity.Deletetesttable;
import com.tuling.springcloud.stock.spring.transaction.mapper.DeletetesttableMapper;
import com.tuling.springcloud.stock.spring.transaction.repository.DeletetesttableRepositoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import out.OutBean;


@SpringBootTest(classes = ZzTestApplication.class)
@RunWith(SpringRunner.class)
public class IocCCCCCCCTest {

    @Test
    public void sdsdsds(){
        Enhancer eh = new Enhancer();
        eh.setSuperclass(beEnhancedClass.class);
        eh.setCallback(new ProxyClass());

        beEnhancedClass o = (beEnhancedClass)eh.create();
        o.ench();
    }

    @Autowired
    AopDemoService demoService;

    @Autowired
    AspecjDemo aspecjDemo;


    @Test
    public void aopTest(){
        demoService.dddd();
    }

    @Autowired
    DeletetesttableMapper deletetesttableMapper;
    @Test
    public void sdsdsd(){
        Deletetesttable deletetesttable = deletetesttableMapper.selectById(7);
        System.out.println(deletetesttable);
    }
    @Autowired
    DataService dataService;
    @Test
    public void getentity(){
        Deletetesttable entity = dataService.getEntity();
        System.out.println(entity);
    }
    //支持的
    @Test
    public void 试试看cglib实现AOP的类是不是不支持事务(){
        dataService.addDeletetesttable();
    }
    //里面影响外面，外面不影响里面
    @Test
    public void 测试传播行为Required_new(){
        dataService.insertMethod1();
    }

    //外面影响里面，里面被try，防止里面影响外面
    @Test
    public void 测试传播行为nested(){
        dataService.insertMethod3();
    }

    //要么一起成功，要么一起失败，异常必须在
    @Test
    public void 测试传播行为REQUIRED(){
        dataService.insertMethod5();
    }

    @Autowired
    DeletetesttableRepositoryImpl deletetesttableRepository;
    @Test
    public void ddsadasdasd(){
        for (int i = 0; i < 2000000; i++) {
            Deletetesttable dtt = new Deletetesttable();
            dtt.setId(10000+i);
            dtt.setName("名字"+i+i+i);
            deletetesttableRepository.save(dtt);
            System.out.println(i);
        }
    }

    @Autowired
    public OutBean outBean;

    @Test
    public void dsdasdfsdfsdf(){
        outBean.ddddd();
    }

    @Autowired
    Zzz zzz;

    @Autowired
    Zzz ccc;

    @Autowired
    Zzzz zzzz;
    @Test
    public void ssdfsdfsdf(){
        System.out.println(zzz);
        System.out.println(ccc);
        System.out.println(zzzz.zzz());
    }

    @Autowired
    private Digest digest;

    @Value("${digest.type}")
    private String type;

    @Test
    public void ddddsdfsdfsdf(){
        String encrypt = digest.encrypt("你好！");
        System.out.println(encrypt);
        System.out.println(type);
    }
    @Autowired
    Environment environment;
    @Test
    public void testEnvironment() throws InterruptedException {
        String javaHome = environment.getProperty("JAVA_HOME");
//        Thread.sleep(3300);
        System.out.println(javaHome);
    }
    @Autowired
    public ConfigTrue configtTrue;
    @Autowired
    public ConfigFalse configFalse;

    @Autowired
    BeanBean beanBeanT;
    @Autowired
    BeanBean beanBeanF;
    @Test
    public void testConfigurantion(){
        BeanBean beanBean1 = configtTrue.beanBeanT();
        BeanBean beanBean2 = configtTrue.beanBeanT();
        BeanBean beanBean3 = configtTrue.beanBeanT();
        System.out.println(beanBean1);
        System.out.println(beanBean2);
        System.out.println(beanBean3);
        System.out.println(beanBeanT);

        BeanBean beanBean11 = configFalse.beanBeanF();
        BeanBean beanBean21 = configFalse.beanBeanF();
        BeanBean beanBean31 = configFalse.beanBeanF();
        System.out.println(beanBean11);
        System.out.println(beanBean21);
        System.out.println(beanBean31);
        System.out.println(beanBeanF);

    }

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    MyAwareBean myAwareBean;

    @Test
    public void ddfdfgdfgdf(){

        MyAwareBean myAwareBean = (MyAwareBean) applicationContext.getBean("myAwareBean");
        ApplicationContext applicationContext = myAwareBean.getApplicationContext();
        System.out.println(applicationContext);
        System.out.println(applicationContext);
        System.out.println(myAwareBean.getApplicationContext());
        System.out.println("+++++++++++++++++++++++");
        System.out.println(myAwareBean.getSssss());
        System.out.println("-------------------------");
        /**
         * bean获取的方式注意一下，FactoryBean获取对象的方式注意一下？factoryBeanImp获取的是FactoryBeanStudent对象
         * &factoryBeanImp才能获取它原来的对象，被鸠占鹊巢了！
         */
        FactoryBeanStudent s = (FactoryBeanStudent) applicationContext.getBean("factoryBeanImp");
        System.out.println(s);
        FactoryBeanImp factoryBeanImp = (FactoryBeanImp)applicationContext.getBean("&factoryBeanImp");
        System.out.println(factoryBeanImp);
    }

}
