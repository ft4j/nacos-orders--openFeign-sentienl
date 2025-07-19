package com.rocketmqexample.rocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalRequestCallback;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 普通消息，同步 异步 单向
 * 顺序消息：同步 异步 单向
 * 延迟消息：同步和异步
 * 批量消息：同步
 * 事务消息：异步
 */
@Component
public class ProducerService {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    //发送普通消息-单项消息
    public void sendSimpleMessage() {
        for (int i = 0; i < 2; i++) {
            rocketMQTemplate.convertAndSend("firstSend", "hello world" + i);
        }
    }

    //发送普通消息 带多个key的
    public void sendSimpleHeaderMessage() {
//        rocketMQTemplate.convertAndSend("firstSend", MessageBuilder.withPayload("哈尔")
//                .setHeader(RocketMQHeaders.KEYS,"123").
//                        setHeader("UNIQ_KEY","asd123")
//                .build());
        Map<String, Object> headers = new HashMap<>();
        headers.put("KEYS","hello");
        headers.put("UNIQ_KEY","一定义msgId");
        rocketMQTemplate.convertAndSend("firstSend111111", "只发送msh",headers);

    }

    public void sendoneway() {
        rocketMQTemplate.sendOneWay("firstSend", "heiha");
    }

    /**
     * 发送普通消息-同步消息
     */
    public void sendSyncMessage() {
        for (int i = 0; i < 10; i++) {
            SendResult firstSend = rocketMQTemplate.syncSend("firstSend", "hello world" + i);
            System.out.println(firstSend);
        }
    }

    /**
     * 发送异步消息
     */
    public void sendAsyncMessage() {
        String messageBody = "hello world";
        for (int i = 0; i < 1; i++) {
            rocketMQTemplate.asyncSend("ccc", messageBody + i,
                    new SendCallback() {
                        public void onSuccess(SendResult sendResult) {
                            System.out.println("成功了");
                        }

                        public void onException(Throwable throwable) {
                            System.out.println("save"+messageBody);
                        }
                    });
        }
    }

    /**
     * rocketMq实现顺序消息
     * hashkey的值可以是任意的，并且不同的消息分组下，hashkey不能相同即可
     * 顺序消息也有 同步顺序消息  异步顺序消息  单向顺序消息
     *         rocketMQTemplate.asyncSendOrderly();
     *         rocketMQTemplate.syncSendOrderly();
     *         rocketMQTemplate.sendOneWayOrderly();
     */
    public void sendOrderly() {
        rocketMQTemplate.sendOneWayOrderly("orderly", "创建1",
                "这个参数会被hash之后达到指定的messagequeue实现排序");
        rocketMQTemplate.sendOneWayOrderly("orderly", "支付1",
                "这个参数会被hash之后达到指定的messagequeue实现排序");
        rocketMQTemplate.sendOneWayOrderly("orderly", "完成1",
                "这个参数会被hash之后达到指定的messagequeue实现排序");


        rocketMQTemplate.sendOneWayOrderly("orderly", "创建2",
                "这个参数会被hash之后达到指定的messagequeue实现排序，这几个字为了和上一个消息区分以实现分组");
        rocketMQTemplate.sendOneWayOrderly("orderly", "支付2",
                "这个参数会被hash之后达到指定的messagequeue实现排序，这几个字为了和上一个消息区分以实现分组");
        rocketMQTemplate.sendOneWayOrderly("orderly", "完成2",
                "这个参数会被hash之后达到指定的messagequeue实现排序，这几个字为了和上一个消息区分以实现分组");
    }

    /**
     * 延迟消息 给普通消息添加一个延迟的时间级别，就能实现延迟消息.
     * 3000值得是消息发送的超时时间
     * messageDelayLevel=1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
     * 描述了各级别与延时时间的对应映射关系。
     * 这个配置项配置了从1级开始各级延时的时间，如1表示延时1s，2表示延时5s，14表示延时10m，可以修改这个指定级别的延时时间；
     * 只有同步消息和异步消息存在延迟消息
     */
    public void delay() {
        rocketMQTemplate.syncSend("ddd", MessageBuilder.withPayload("3").build(), 3000,1);
        rocketMQTemplate.asyncSend("ddd", MessageBuilder.withPayload("3").build(), new SendCallback() {
            public void onSuccess(SendResult sendResult) {
                System.out.println("成功");
            }

            public void onException(Throwable throwable) {
                System.out.println("失败");
            }
        }, 3000,1);

        rocketMQTemplate.sendAndReceive("ddd", "dddd",new RocketMQLocalRequestCallback() {

            @Override
            public void onSuccess(Object message) {

            }

            public void onException(Throwable throwable) {
                System.out.println("失败");
            }
        },"这个key保证顺序", 3000,1);
    }
    @Autowired
    RocketMQTemplate myRocketMqTemplate;
    /**
     * 事务消息
     * 重试时间的16个层级：10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
     */
    public void transactionMessage() {
        MessageBody ms = new MessageBody();
        ms.setKey("body key");
        ms.setMessageData(new MessageData("这是数据1","这是数据2"));
        //设置一个字符串header，用来在本地事务执行的时候，进行判断是哪个消息，应该执行哪一块的事务
        Message<MessageBody> m = MessageBuilder.withPayload(ms)
                .setHeader(RocketMQHeaders.KEYS,MessageBody.className).build();
        System.out.println("发送事务消息！");
        //事务消息发送，一开始发送一个半消息
        myRocketMqTemplate.sendMessageInTransaction("transaction", m, null);
    }

    @RocketMQTransactionListener(rocketMQTemplateBeanName = "myRocketMqTemplate")
    class TransactionMessage implements RocketMQLocalTransactionListener {

        @SneakyThrows
        public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
            MessageHeaders headers = message.getHeaders();
            Object tou = headers.get(RocketMQHeaders.KEYS);
            if(MessageBody.className.equals(tou)){
                System.out.println("拿到对应事务消息了，处理在这个if下进行");
                System.out.println("处理本地事务");
                Object payload = message.getPayload();
                Class<?> aClass = Class.forName(MessageBody.className);
                Method getName = aClass.getMethod("getMessageData");
                Object invoke = getName.invoke(payload);
                ObjectMapper m = new ObjectMapper();
                String s = m.writeValueAsString(invoke);
                //在这里处理本地事务
                System.out.println("保存数据库"+s);
                System.out.println("RocketMQLocalTransactionState.UNKNOWN！！！！");
                return RocketMQLocalTransactionState.UNKNOWN;
                //return RocketMQLocalTransactionState.ROLLBACK;当本都事务出错了
                /**
                 * return RocketMQLocalTransactionState.UNKNOWN;出现那种可能需要时间就能恢复异常，就在返回unknown
                 * 比如出现数据库连不上这种异常
                 */
            }else{
                //在这里处理另外的本地事务
                System.out.println("处理本地事务");
                return RocketMQLocalTransactionState.UNKNOWN;
            }
        }

        public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
            MessageHeaders headers = message.getHeaders();
            Object tou = headers.get(RocketMQHeaders.KEYS);
            System.out.println("重试了！！！！！！！！checkLocalTransaction");
            if("123".equals(tou)){
                System.out.println("拿到对应事务消息了，处理在这个if下进行");
                System.out.println("处理本地事务重试");
                return RocketMQLocalTransactionState.COMMIT;
            }
            //当本地事务处理出现返回值为UNKNOW时，进本方法进行重试
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd hh:mm:ss");
            String format = sdf.format(new Date());
            System.out.println("重试本地事务"+format);
            return RocketMQLocalTransactionState.UNKNOWN;
        }
    }

    public void 发送普通消息(){
        rocketMQTemplate.sendOneWay("GroupFirst_topic","这是我测试发送的消息");
    }

    public void 测试相同topic不同消费者组(){
        rocketMQTemplate.sendOneWay("sameTopicDiffConsumerGroup",
                "这是我测试发送的消息，测试相同topic不同消费者组");
    }

    public void sql92(){
        for (int i = 0; i < 10; i++) {
            HashMap<String, Object> harder = new HashMap<>(1);
            harder.put("a", String.valueOf(i));
            rocketMQTemplate.convertAndSend("GroupFirst_topic1","过滤消息测试" + i, harder);
        }
    }

    /**
     * syncSend可以发送字符串，也可以将对象包装在Message中，以对象形式发送，有返回值
     *
     * convertAndSend本质其实是syncSend，但是不提供返回值，会将发送情况进行一个日志打印
     * 他的消息参数是Object，内部会对对象进行自动包装--doConvert()方法
     */
    public void 发送List对象(){
        List<String> l = new ArrayList();
        l.add("张三");
        l.add("李四");

        /**
         * MessageBuilder.withPayload(l).build())  创建的message（它都不带Topic）和自己写Message并发送，不是同一个对象
         *         这个对象需要放在defaultMQProducer中使用
         */
        org.apache.rocketmq.common.message.Message message =
                new org.apache.rocketmq.common.message.Message("topic1","tag1","水哥".getBytes());
        //怎么发
        rocketMQTemplate.syncSend("GroupFirst_topic2",MessageBuilder.withPayload(l).build());
        /**
         * 这也是可以发送成功的
         * 其实rocketMq本质只能发送Byte[]的数据，rocketMqTemplate直接发送对象，或者发送通过MessageBuilder创建的
         * org.springframework.messaging.Message对象，其实最终都会被转为Json串后被getByte();方法转为byte[]后发送
         */
        rocketMQTemplate.syncSend("GroupFirst_topic2",l);
//        rocketMQTemplate.syncSend("GroupFirst_topic2","zzxczxc");
        List<String> lx = new ArrayList();
        lx.add("王五");
        lx.add("刘六");
        rocketMQTemplate.convertAndSend("GroupFirst_topic2",lx);
    }

    public void sendGroupFirst_topic2(){
        rocketMQTemplate.syncSend("GroupFirst_topic2","我是张三！");
    }




    /**
     * rocketMq新练习
     * rocketMq发送消息根据轮询方式进行负载均衡
     */
    public void syncMessage(){
        //本身是一个syncSend，底层由返回值的，但是convertAndSend将发送结果以日志形式记录了，封装之后，不再返回
        rocketMQTemplate.sendOneWay("GroupFirst_topic:tag1","单项消息1");
        rocketMQTemplate.sendOneWay("GroupFirst_topic:tag1","单项消息2");
        rocketMQTemplate.sendOneWay("GroupFirst_topic:tag1","单项消息3");
        rocketMQTemplate.sendOneWay("GroupFirst_topic:tag1","单项消息4");
        rocketMQTemplate.sendOneWay("GroupFirst_topic:tag2","单项消息11");
        rocketMQTemplate.sendOneWay("GroupFirst_topic:tag2","单项消息21");
        rocketMQTemplate.sendOneWay("GroupFirst_topic:tag2","单项消息31");
        rocketMQTemplate.sendOneWay("GroupFirst_topic:tag2","单项消息41");
        rocketMQTemplate.sendOneWay("GroupFirst_topic:tag3","单项消息123");
        rocketMQTemplate.sendOneWay("GroupFirst_topic:tag3","单项消息223");
        rocketMQTemplate.sendOneWay("GroupFirst_topic:tag3","单项消息323");
        rocketMQTemplate.sendOneWay("GroupFirst_topic:tag3","单项消息423");
    }

    /**
     * 批量消息发送
     * 批量消息只有同步消息
     */
    public void 发送批量消息(){
        List<Message<String>> messages = new ArrayList<Message<String>>();
        for (int i = 0; i < 3; i++) {
            messages.add(MessageBuilder.withPayload("消息数据" + i).build());
        }
        SendResult groupFirst_topic = rocketMQTemplate.syncSend("GroupFirst_topic", messages);
        SendStatus sendStatus = groupFirst_topic.getSendStatus();
        System.out.println(sendStatus);
        MessageQueue messageQueue = groupFirst_topic.getMessageQueue();
        groupFirst_topic.setMessageQueue(messageQueue);
        System.out.println("");
    }


    public void dd(){
        //发送后消费者会返回一个消息回来，本质其实是消费也作为生产者，给生产者作为消费者的方式发送一个消息
        String o = rocketMQTemplate.sendAndReceive("", "", String.class);
    }


    public String sendOneMsg(){
        SendResult sendResult = rocketMQTemplate.syncSend("testMsg", "测试消息的消息体");
        return sendResult.getSendStatus().toString();
    }

    /**
     * 试试看header可以放什么
     */
    public void sdsdsdsdsd(){
        SendResult getHeaderAndId = rocketMQTemplate.syncSend("getHeaderAndId", new ArrayList<>());

    }

}
