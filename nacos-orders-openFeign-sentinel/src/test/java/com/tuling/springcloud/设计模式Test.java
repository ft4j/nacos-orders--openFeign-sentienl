package com.tuling.springcloud;

import com.tuling.springcloud.orders.设计模式.结构型一代理模式.消费者;
import com.tuling.springcloud.orders.设计模式.结构型一代理模式.中介;
import com.tuling.springcloud.orders.设计模式.单例模式.EhanSingleton;
import com.tuling.springcloud.orders.设计模式.单例模式.LanhanSingleton;
import com.tuling.springcloud.orders.设计模式.工厂模式.A工厂模式.Bmw;
import com.tuling.springcloud.orders.设计模式.工厂模式.A工厂模式.BmwFactory;
import com.tuling.springcloud.orders.设计模式.工厂模式.B工厂方法模式.Bmw330Factory;
import com.tuling.springcloud.orders.设计模式.工厂模式.B工厂方法模式.Bmw530Factory;
import com.tuling.springcloud.orders.设计模式.工厂模式.B工厂方法模式.BmwB;
import com.tuling.springcloud.orders.设计模式.工厂模式.C抽象工厂模式.AbstractFactory;
import com.tuling.springcloud.orders.设计模式.工厂模式.C抽象工厂模式.Bmw330FactoryC;
import com.tuling.springcloud.orders.设计模式.工厂模式.C抽象工厂模式.Bmw530FactoryC;
import com.tuling.springcloud.orders.设计模式.建造者模式.Meal;
import com.tuling.springcloud.orders.设计模式.建造者模式.MealBuilder;
import com.tuling.springcloud.orders.设计模式.建造者模式.Meala;
import com.tuling.springcloud.orders.设计模式.结构型一桥接模式.BrandHuawei;
import com.tuling.springcloud.orders.设计模式.结构型一桥接模式.Computer;
import com.tuling.springcloud.orders.设计模式.结构型一桥接模式.PcComputer;
import com.tuling.springcloud.orders.设计模式.结构型一装饰器模式.Component;
import com.tuling.springcloud.orders.设计模式.结构型一装饰器模式.ConcreteComponent;
import com.tuling.springcloud.orders.设计模式.结构型一装饰器模式.ConcreteDecorator;
import com.tuling.springcloud.orders.设计模式.结构型一装饰器模式.Decorator;
import com.tuling.springcloud.orders.设计模式.结构型一适配器模式.Adapter;
import com.tuling.springcloud.orders.设计模式.结构型一适配器模式.UseTarget;
import com.tuling.springcloud.orders.设计模式.行为模式一观察者模式.Observer;
import com.tuling.springcloud.orders.设计模式.行为模式一观察者模式.Subject;
import com.tuling.springcloud.orders.设计模式.行为模式一观察者模式.SubscriptionSubject;
import com.tuling.springcloud.orders.设计模式.行为模式一观察者模式.WeixinUser;
import org.junit.Test;

/**
 * 创建型模式：共5种：工厂方法模式、抽象工厂模式、单例模式、建造者模式、原型模式
 * 结构型模式：共7种：(适配器模式、桥接模式、代理模式、装饰器模式)、外观模式、组合模式、享元模式
 * 行为型模式：共11种：(观察者模式、命令模式、策略模式)、模板方法模式、责任链模式、访问者模式、中介者模式、迭代器模式、状态模式、备忘录模式、解释器模式
 */
public class 设计模式Test {
    /**
     * 简单工厂在添加新的产品时，需要修改工厂代码，开闭原则不成立
     */
    @Test
    public void 简单工厂(){
        Bmw car = BmwFactory.createCar("330");
        System.out.println(car);
    }

    /**
     * 工厂方法添加新产品，需要每次添加一对产品和产品工厂组合
     * 这什么玩意？
     */
    @Test
    public void 工厂方法(){
        Bmw330Factory b330 = new Bmw330Factory();
        BmwB car1 = b330.createBmw();

        Bmw530Factory b530 = new Bmw530Factory();
        BmwB car2 = b530.createBmw();
        System.out.println(car1);
        System.out.println(car2);
    }

    @Test
    public void 抽象工厂(){
        AbstractFactory a = new Bmw330FactoryC();
        a.createEngine();
        a.createAirCondition();

        AbstractFactory b = new Bmw530FactoryC();
        b.createEngine();
        b.createAirCondition();

        System.out.println(a);
        System.out.println(b);
    }


    /**
     * 就是吧一个对象的set过程，给一个抽象类处理
     */
    @Test
    public void 建造者模式(){
        MealBuilder mb = new Meala();
        mb.buildFood("汉堡");
        mb.buildDrink("可乐");
        Meal t = mb.get套餐();
        System.out.println(t);
    }

    @Test
    public void 单例模式(){
        LanhanSingleton l = LanhanSingleton.getLanhanSingleton();
        System.out.println(l);
        LanhanSingleton l1 = LanhanSingleton.getLanhanSingleton();
        System.out.println(l1);



        EhanSingleton ehanSingleton = EhanSingleton.getEhanSingleton();
        System.out.println(ehanSingleton);
        EhanSingleton ehanSingleton1 = EhanSingleton.getEhanSingleton();
        System.out.println(ehanSingleton1);
    }

    /**
     * 让一个方法被前后都加上方法，让他的功能得到加强
     * 消费者和中介都能买房，但是客户经过中介代理，客户只要付钱，其他的买房步骤就由中介代替了
     * 和装饰器模式的区别为，装饰器模式的方法增强是动态的，可以在代码实现出进行动态调整
     * 比如说AOP，一旦一个方法被代理，那么它本身的创建过程就被代码覆盖，不可见了
     */
    @Test
    public void 代理模式(){
        中介 z = new 中介(new 消费者());
        z.buy();
    }


    /**
     * 和代理有点像，不过装饰器对于目标方法的加强是可以动态的
     * 装饰模式的实现则不同，它可以根据自己需求去创建一个Component的实现类，自己觉得是不是被装饰
     */
    @Test
    public void 装饰器模式(){
        Component component = new ConcreteDecorator(new ConcreteComponent());
        component.execute();
        System.out.println("--------------------");
        Component c = new ConcreteComponent();
        c.execute();
    }



    /**
     * 实例化一个电脑对象的时候，需要给的构造方法传入一个品牌。
     * 这样，可以在电脑对象中使用品牌。
     * 当一个对象需要使用另一个对象，且被使用的对象可能有多个实现的时候，使用桥接模式。
     * 这样可以这一个对象使用被使用对象一个父接口作为参数，每次只要修改传入的对象而不修改代码，就能实现两个对象的功能连接
     */
    @Test
    public void 桥接(){
        Computer c = new PcComputer(new BrandHuawei());
        c.showInfo();
    }


    /**
     * 为什么使用适配器模式？
     * UseTarget需要使用一个接口Target作为参数，这时候，实现Target需要使用到类Adaptee的方法（比如Adaptee是一个外部的sdk，我们知道功能但是不能该改代码）
     * 并且target接口的实现可能就是Adaptee的一个方法，此时我们需要一个Adapter去适配Target和Adaptee
     * 让一个adapter去实现Target接口，然后在adapter中去调用Adaptee的方法
     */
    @Test
    public void 适配器模式(){
        UseTarget useTarget = new UseTarget(new Adapter());
        useTarget.use();
    }

    /**
     * 将一个接口的一个或者多个实现类的对象，存入一个List中
     * 使用一个方法循环这个list，循环中会调用接口的某个方法
     * 而循环list的方法可以根据接口方法的参数类型，给定参数，然后循环list
     * 让接口实现类的对象，集体被调用
     */
    @Test
    public void 观察者模式(){
        Observer ob1 = new WeixinUser("刘备");
        Observer ob2 = new WeixinUser("关羽");
        Observer ob3 = new WeixinUser("张飞");

        Subject subject = new SubscriptionSubject();
        subject.attach(ob1);
        subject.attach(ob2);
        subject.attach(ob3);

        subject.notify("大兵压境");

    }

}
