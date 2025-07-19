package com.tuling.springcloud.service;

import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import com.tuling.springcloud.openFeignInterface.FeignClientForHystrixCommand;
import com.tuling.springcloud.reqAndRes.RequestDto;
import com.tuling.springcloud.reqAndRes.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager.CORE_SIZE;
import static com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager.EXECUTION_ISOLATION_STRATEGY;

@Service
public class OpenFeignAndHystrixService extends 统一处理fallback{
    @Autowired
    FeignClientForHystrixCommand feignClientForHystrixCommand;

    @HystrixCommand(fallbackMethod = "xx",
            commandProperties = {
            @HystrixProperty(name=HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD,value="6"),
            @HystrixProperty(name= HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE,value="60"),
    })
    public ResponseDto hystrixDest(RequestDto requestDto){
        ResponseDto responseDto = feignClientForHystrixCommand.hystrixDest(requestDto);
        return responseDto;
    }
    //HystrixCommandAspect这地方让它起效的
    @HystrixCommand(fallbackMethod = "xx",
            //线程池隔离的参数
            groupKey = "group1",//同组就用同一个线程池
            threadPoolKey = "pool",//指明是哪个线程池
            //commandKey = "",//不配置，默认就是方法名，不配也没事
            commandProperties = {
                    @HystrixProperty(name=EXECUTION_ISOLATION_STRATEGY,value = "THREAD"),
                    //@HystrixProperty(name=EXECUTION_ISOLATION_STRATEGY,value = "SEMAPHORE"),这是信号量隔离
                    @HystrixProperty(name=HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD,value="6"),
                    @HystrixProperty(name= HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE,value="60"),
                    //@HystrixProperty(name=EXECUTION_ISOLATION_STRATEGY,value = "SEMAPHORE"),信号量隔离
                    //@HystrixProperty(name=EXECUTION_ISOLATION_SEMAPHORE_MAX_CONCURRENT_REQUESTS,value = "10"),信号量数值
                    //EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS="2000" 设置隔离的超时时间，超时会主动释放线程资源，这里设置为2秒。如果是信号量，只会在结束之后判断是否超时。默认值1000
                    //EXECUTION_ISOLATION_THREAD_INTERRUPT_ON_TIMEOUT，设置是否打开超时终端，默认TRUE
            },
            threadPoolProperties ={
                    @HystrixProperty(name=HystrixPropertiesManager.CORE_SIZE,value = "10"),//这里可以配置线程池的信息
            })
    public ResponseDto 本地接口(RequestDto requestDto){
        System.out.println(Thread.currentThread().getName());
        int s = 1/0;
        ResponseDto d = new ResponseDto();
        d.setRespMessage("ddddd");
        return d;
    }


}
