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
//		amqpTemplate.convertAndSend("ld.quern", Arrays.asList("hello rabbitmq", "hello world")); //对应(Direct模式)
//		amqpTemplate.convertAndSend("ld.quern", "hello rabbitmq"); //对应(Direct模式)
//		amqpTemplate.convertAndSend("exchange", "topic.message", "hello world");//对应(Topic转发模式)
		amqpTemplate.convertAndSend("fanoutExchange", "", "hello rabbitmq"); //对应(Fanout Exchange形式)

	}

	/**
	 * 可以传一个对象，但是默认会被虚拟化
	 * 可以使用springboot自带的转json
	 */
//	public void send() {
//		User user = new User(); // 实现Serializable接口
//		user.setUserName("hlhdidi");
//		user.setPassWord("123");
//		amqpTemplate.convertAndSend("ld.quern", user);
//	}

}
