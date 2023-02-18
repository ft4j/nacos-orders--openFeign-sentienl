package com.tuling.springcloud.orders.ThreadLocalPackage;

public class ParamsBand {
    private static final ThreadLocal<String> local = new ThreadLocal<String>();
    public static void setParam(String str){
        local.set(str);
    }

    public static String getParam(){
        return local.get();
    }
    public static void remove(){
        local.remove();
    }
}
