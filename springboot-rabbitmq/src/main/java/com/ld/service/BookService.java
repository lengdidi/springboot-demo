package com.ld.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.ld.bean.Book;

@Service
public class BookService {
	@RabbitListener(queues = "lengdi.news")
	public void receive(Book book) {
		System.out.println("收到消息" + book);
	}
	
	@RabbitListener(queues = "lengdi")
	public void receive02(Message message) {
		System.out.println(message.getBody());
		System.out.println(message.getMessageProperties());
	}
}
