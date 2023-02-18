package com.example.sentineldemo.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.example.sentineldemo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/hello")
public class HelloController {
    /**
     * sentinel是根据资源进行保护的，一个资源可以绑定流控降级规则
     * 例如RESOURCE_NAME这个资源，下次查看可以直接搜索
     * 会发现它被绑定了一个流控降级规则 并且在被流控降级的接口中被使用
     * 例如RESOURCE_NAME这个资源被访问到，就会验证流控规则，如果不符合规则就胡进流控处理方法
     */
    private static final String RESOURCE_NAME="hello";
    private static final String USER_RESOURCE_NAME="user";
    private static final String DEGRADE_RESOURCE_NAME="degrade";
    @RequestMapping("/degrade")
    @SentinelResource(value="DEGRADE_RESOURCE_NAME",entryType = EntryType.IN
    ,blockHandler = "degradeRuleHandler")
    public User degrade(String id){
        int s = 1/0;
        return new User(id,"yuhanyan");
    }

    public User degradeRuleHandler(String id,BlockException exception){
        return new User(id,"错了，保护");
    }

    /**
     * 使用注解开发sentinel
     * 1、添加依赖 2、配置使用注解开发sentinel的bean 杂牌application
     * value定义保护的资源，blockHandlers设置被流控降级后的方法（默认情况下该方法需要声明在同一个类中）
     * fallback 接口出现异常时进什么方法处理
     * 流控降级的方法优先于fallback
     * exceptionsToIgnore哪些异常不会被fallback处理
     * @return
     */
    @RequestMapping("/user")
    @SentinelResource(value=USER_RESOURCE_NAME,blockHandler = "blockHandlerFOrUser"
            ,fallback = "exe",entryType = EntryType.IN,exceptionsToIgnore = {ArithmeticException.class})
    //,blockHandlerClass = User.class 这个参数可以让blockHandler方法不在本类中，可以指定类
    //当然如果被设置在User这和类中，那么需要将这方法声明为static的
    public User getUser(String id){
        //double d = 1/0;
        return new User(id,"yuhanyan");
    }

    /**
     * 注意这方法必须public
     * 返回值一定和原方法保持一直
     * 参数要包含原方法的参数，且顺序一样  并且加上BlockException
     * 这个方法除了可以作为留空的处理方法，也可以是降级之后的处理方法，也可以当成系统规则的处理方法
     * BlockException可以区分是什么规则的从处理方法
     * 下面是BlockException的实现类，根据爆出的一场可以确定是什么异常
     * FlowException (com.alibaba.csp.sentinel.slots.block.flow)
     * DegradeException (com.alibaba.csp.sentinel.slots.block.degrade)
     * AuthorityException (com.alibaba.csp.sentinel.slots.block.authority)
     * SystemBlockException (com.alibaba.csp.sentinel.slots.system)
     * @param id
     * @return
     */
    public User blockHandlerFOrUser(String id,BlockException exception){
        exception.printStackTrace();
        return new User(id,"被流控了！！");
    }
    public User exe(String id,Throwable e){
        e.printStackTrace();
        System.out.println("User接口出现了异常，在本方法处理！");
        return new User(id,"异常处理方法");
    }

    //在此进行流量控制
    @RequestMapping("/hello")
    public String hello(){
        Entry entry = null;
        try {
            //sentinel是针对资源进行限制的，这一点和synchronized关键字相似
            entry = SphU.entry(RESOURCE_NAME);
            //被保护的业务
            String str = "hello world";
            log.info("====="+str+"=====");
        } catch (BlockException e) {
            log.info("blocked!");
            //这里其实就是流控降级的处理方法
            return "被流控了";
        } catch (Exception e) {
            //若需要配置降级规则，需要通过这种方式记录业务异常
            Tracer.traceEntry(e,entry);
        }finally{
            //释放资源
            if(entry != null){
                entry.exit();
            }
        }
        return null;
    }

    //设置流控规则 流控规则分为 流量控制和线程数控制
//    public static final int FLOW_GRADE_THREAD = 0;
//    public static final int FLOW_GRADE_QPS = 1;
    @PostConstruct
    public static void initFlowRules(){
        //流控规则设置 FlowRule就是流控规则对象
        List<FlowRule> list = new ArrayList<FlowRule>();
        //流控
        FlowRule flowRule = new FlowRule();
        //设置受保护的资源,这里保护的是这个hello
        flowRule.setResource(RESOURCE_NAME);
        //设置流控规则 这里设置的是qps限制的流控规则
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //设置这个值表示这个服务的qps不能大于1，否则就会被限流
        flowRule.setCount(1);
        list.add(flowRule);
        //下面这个规则是使用注解开发sentinel
        FlowRule flowRule2 = new FlowRule();
        //设置受保护的规则
        flowRule2.setResource(USER_RESOURCE_NAME);
        //设置流控规则
        flowRule2.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //设置这个值表示这个服务的qps不能大于1，否则就会被限流
        flowRule2.setCount(1);
        list.add(flowRule2);

        //给已经设置好的流控规则加入流控管理管理器
        FlowRuleManager.loadRules(list);
    }
    //设置熔断降级规则  慢调用比例、异常比例、异常数策略
//    public static final int DEGRADE_GRADE_RT = 0;
//    public static final int DEGRADE_GRADE_EXCEPTION_RATIO = 1;
//    public static final int DEGRADE_GRADE_EXCEPTION_COUNT = 2;
    @PostConstruct
    public static void initDegradeRules(){
        //流控规则设置 FlowRule就是流控规则对象
        List<DegradeRule> list = new ArrayList<DegradeRule>();
        //设置降级规则
        DegradeRule degradeRule = new DegradeRule();
        //设置保护的资源
        degradeRule.setResource(DEGRADE_RESOURCE_NAME);
        //设置降级保护的类型
        degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
        //慢调用比例模式下慢调用临界值  这里设置大于大于2秒就是慢调用
        degradeRule.setCount(2);
        //设置熔断的时间长度 单位秒
        degradeRule.setTimeWindow(10);
        //即使触发了熔断条件，如果并发数量不大于到这个数值就不会触发熔断，设置为2其实最少3次
        degradeRule.setMinRequestAmount(2);
        //设置统计的时常，也就是在这一段时间内出现熔断降级类型的数量，在这个时间内超出数量才会出现熔断降级
        // 比如2天出了3个异常就不会被熔断降级，但是1分钟出现2次就会被降级，这里设置了1分钟
        //默认1秒
        degradeRule.setStatIntervalMs(60*1000);
        /**
         * 异常的规则设置的意义为  在1分钟内出现至少2次请求且至少出现了2次异常就会出发熔断降级
         * 熔断降级的持续时间为10秒
         * 和流控规则不一样，流控规则超出限制就会将超出的请求直接进入对应的处理方法
         * 但是熔断降级会有一个恢复时间，恢复之前进入熔断降级方法
         * 10秒之后就会再次进入正常的方法
         * 但这是半开状态，此时如果调用被保护的方法立刻出错或者依然慢调用（根据对应规则），就会再次熔断
         * 不会根据设置的条件再次进入熔断方法
         */
        list.add(degradeRule);
        //给已经设置好的流控规则加入流控管理管理器
        DegradeRuleManager.loadRules(list);
    }

}
