package com.ld.mq;

import java.util.Arrays;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ld.bean.User;

@Component
public class SendMSG {

	@Autowired
	private AmqpTemplate amqpTemplate;

	public void sendmsg() {
//		amqpTemplate.convertAndSend("ld.quern", Arrays.asList("hello rabbitmq", "hello world"));
//		amqpTemplate.convertAndSend("ld.quern", "hello rabbitmq");
//		amqpTemplate.convertAndSend("exchange", "topic.message", "hello world");
		amqpTemplate.convertAndSend("fanoutExchange", "", "hello rabbitmq");

	}

//	public void send() {
//		User user = new User(); // 实现Serializable接口
//		user.setUserName("hlhdidi");
//		user.setPassWord("123");
//		amqpTemplate.convertAndSend("ld.quern", user);
//	}

}
