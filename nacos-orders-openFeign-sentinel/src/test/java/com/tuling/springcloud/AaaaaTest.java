package com.tuling.springcloud;

import com.tuling.springcloud.orders.domain.User;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AaaaaTest {
    @Test
    public void fdfdfd(){

        User user = new User();
        Class<? extends User> aClass = user.getClass();
    }
    /**
     * 一段java代码的执行过程
     * 1、编写java代码
     * 2、通过编译器编译成class文件
     * 3、运行java，由类加载器加载class到方法区（方法区有什么？class对象可以get出来的东西）
     * 4、由线程创建对象，设置对象信息，对象存放在堆里
     * 5、字节码执行引擎执行代码
     * 6、将对象的引用根据代码执行过程进行压栈和弹出，这个和代码的执行过程逻辑匹配
     * 7、这个栈包括本地方法栈和普通栈
     * 8、出现CPU时间的切换时，将代码运行的位置记录到程序计数器中
     * 9、下一次获取时间片时，读取程序计数器的行数，从上次执行的位置继续往下执行
     * 10、执行结束，销毁线程！
     */

    @Test
    public void ssdd(){
        dd("b");
    }

    void dd(String d){
        String s = "ab";
        String s1 = "a"+d;
        System.out.println(s==s1);
    }



    @Test
    public void sdsds(){
        String intern = "aa".intern();
        String dd = "aaddddddddddddddddddddddddddddddddddddddddddddddddddd";
        String dd1 = "aaddddddddddddddddddddddddddddddddddddddddddddddddddd";
        System.out.println(dd == dd1);

        ExecutorService es = Executors.newSingleThreadExecutor();
        Executors.newScheduledThreadPool(2);
    }

}
