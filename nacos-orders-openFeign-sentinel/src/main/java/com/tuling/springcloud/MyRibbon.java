package com.tuling.springcloud;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import com.tuling.springcloud.orders.ThreadLocalPackage.ParamsBand;

import java.util.List;

public class MyRibbon extends AbstractLoadBalancerRule {
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    public Server choose(Object key) {
        return choose(getLoadBalancer(),key);
    }

    public Server choose(ILoadBalancer iLoadBalancer,Object key){
        List<Server> reachableServers = iLoadBalancer.getReachableServers();
        String param = ParamsBand.getParam();
        Integer integer = Integer.valueOf(param);
        Server server = reachableServers.get(integer);
        return server;
    }
}
