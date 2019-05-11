package com.ld.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.ld.bean.User;

@Component
public class MyReceive {
    @RabbitListener(queues = "ld.quern")
    public void process(String s) {
        System.err.println("re" + s);
    }

//	@RabbitListener(queues = "ld.quern")
//	public void process1(User user) { // 用User作为参数
//		System.out.println("Receive1:" + user);
//	}

    @RabbitListener(queues = "topic.message") // 监听器监听指定的Queue
    public void process1(String str) {
        System.err.println("message:" + str);
    }

    @RabbitListener(queues = "topic.messages") // 监听器监听指定的Queue
    public void process2(String str) {
        System.err.println("messages:" + str);
    }

    @RabbitListener(queues = "fanout.A")
    public void processA(String str1) {
        System.err.println("ReceiveA:" + str1);
    }

    @RabbitListener(queues = "fanout.B")
    public void processB(String str) {
        System.err.println("ReceiveB:" + str);
    }

    @RabbitListener(queues = "fanout.C")
    public void processC(String str) {
        System.err.println("ReceiveC:" + str);
    }
}
