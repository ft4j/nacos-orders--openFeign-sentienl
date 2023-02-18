package com.rocketmqexample;

import com.rocketmqexample.rocket.MessageBody;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
public class SourceProductTest {
    @Test
    public void dd(){
        System.out.println("ddd");
    }

    @Test
    public void 原生发送普通消息() throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer dmp = new DefaultMQProducer("source_producer_group");
        dmp.setNamesrvAddr("localhost:9876");
        dmp.setDefaultTopicQueueNums(10);
        dmp.start();
        for (int i = 0; i < 1; i++) {
            Message message = new Message("TopicTest","*","中国有斯哈".getBytes());

            SendResult send = dmp.send(message);
            System.out.println(send.getMsgId());
            System.out.println(send.getOffsetMsgId());
            System.out.println("ddd");
        }
        dmp.shutdown();
    }

    @Test
    public void s(){
        String dd = "dddxxs";
        Integer i = dd.hashCode();
        i = Math.abs(i);
        System.out.println(i);
        int d = i%4;
        System.out.println(d);
        BigDecimal b = new BigDecimal("12");
        b.add(new BigDecimal("3"));
        BigDecimal abs = b.abs();



    }

    @Test
    public void dsdsds(){
        List<String> l = new ArrayList<>();
        l.add("1");l.add("2");l.add("3");l.add("4");l.add("5");l.add("6");l.add("7");
        l.add("8");l.add("11");
//        l.add("11");l.add("12");l.add("13");l.add("14");l.add("15");l.add("16");l.add("17");l.add("18");
        List<String> l1 = getL(l);
        System.out.println(l1);
    }

    public List<String> getL(List<String> l){

        int num = 8;
        if(l.size()<=8){
            return l;
        }else{
            Set<String> s = new HashSet<String>();
            while(true){
                Random r = new Random();
                s.add(l.get(r.nextInt(l.size())));
                if(s.size()>=num){
                    return new ArrayList<>(s);
                }
            }
        }
    }


}
