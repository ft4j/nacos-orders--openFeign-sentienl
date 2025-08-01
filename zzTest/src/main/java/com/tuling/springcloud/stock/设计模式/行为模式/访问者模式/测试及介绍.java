package com.tuling.springcloud.stock.设计模式.行为模式.访问者模式;

import org.junit.Test;

/**
 * 访问者模式是观察者模式的复杂版本，观察者模式调用自身方法，访问者模式则是对象被外部方法使用
 * 区别在于：观察者模式属于接口的实现中的方法被批量调起
 *         访问者模式属于接口的具体实现被外部使用
 *
 *
 * 观察者模式，一系列的对象可能会被多个不同的业务流程使用。
 * 比如此时有一系列的员工，分为临时工和全职员工（注意，他们都实现了员工接口）。他们的集合会被HR部门和财务部门使用
 * 员工的集合是一样的，但是HR部门和财务部门会将这些员工集合拿去做不同的操作
 * 这时候，员工信息不会被污染，员工信息也不会跟具体部门的功能强耦合，即使添加了新的部门，只需要添加新的部门作为访问者即可
 * 部门就是访问者，访问者不会修改任何被访问对象的信息
 * 并且，由于被访问实现了同一接口，他们可以被循环调起并访问
 *
 * 这就是观察者模式的应用场景
 */
/**
 * 访问者接口  -- 访问者，它里面有个方法a（如果被访问者有n个实现，那么方法a需要重载n次），用来访问被访问者，参数是”被访问者接口“的具体实现
 * 被访问者接口 -- 被访问者，它里面有一个参数，就是”访问者接口“，并且存在一个方法b，参数是”访问者接口“
 * 方法b中调用方法a，参数为this（”被访问者接口“的具体实现）
 */
public class 测试及介绍 {
    @Test
    public void ddd(){
        EmployeeList el = new EmployeeList();
        el.addEmployee(new B临时工("张三",30,12));
        el.addEmployee(new B全职员工("李四",30,60));

        el.userEmployee(new AHRDepartment());
        System.out.println("---------");
        el.userEmployee(new AFADDepartment());
    }
}










