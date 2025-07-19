package com.tuling.springcloud.orders.springEvent;

import org.springframework.context.ApplicationEvent;

public class EmailEvent extends ApplicationEvent {
    // 标题
    private String subject;
    // 内容
    private String content;
    // 发件人
    private String sender;
    // 授权码
    private String authorizationCode;
    // 收件人
    private String receiver;


    /**
     * @param source
     */
    public EmailEvent(Object source) {
        super(source);

    }

    public EmailEvent(Object source,String subject, String content, String receiver) {
        super(source);
        this.subject = subject;
        this.content = content;
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }


    public String getSender() {
        return sender;
    }


    public void setSender(String sender) {
        this.sender = sender;
    }


    public String getReceiver() {
        return receiver;
    }


    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
