package com.ld;

import com.ld.bean.Book;
import com.ld.mq.SendMSG;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqApplicationTests {
    @Autowired
    private SendMSG sendMSG;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpadmin;

    @Test
    public void contextLoads() {
        sendMSG.sendmsg();
//        sendMSG.send();
    }

    @Test
    public void createExchange() {
//		amqpadmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
//		System.out.println("创建完成");
//		amqpadmin.declareQueue(new Queue("amqpadmin.quern", true));
//		System.out.println("创建完成");
        // 创建绑定规则
        amqpadmin.declareBinding(
                new Binding("amqpadmin.quern", Binding.DestinationType.QUEUE, "amqpadmin.exchange", "amqp.haha", null));
        System.out.println("绑定成功");

//		amqpadmin.deleteQueue(queueName);	//删除队列
//		amqpadmin.deleteExchange(exchangeName)	//删除交换
        //amqpAdmin.
    }

    @Test
    public void contextLoads2() {
        // Message需要自己定义;定义一个消息体和消息头
//		rabbitTemplate.send();
        //
//		rabbitTemplate.convertAndSend();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("msg", "这是第一条数据");
        map.put("data", Arrays.asList("helloworld", 123, true));
        // 对象被默认序列化以后发出去
        rabbitTemplate.convertAndSend("exchange.direct", "lengdi.news", new Book("西游记", "吴承恩"));
        rabbitTemplate.convertAndSend("exchange.direct", "lengdi.news", map);
    }

    // 接收数据，如何将数据自动的转换成json
    @Test
    public void receive() {
        Object o = rabbitTemplate.receiveAndConvert("lengdi.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    @Test
    public void sendMsg() {
        rabbitTemplate.convertAndSend("exchange.fanout", "", new Book("西游记", "吴承恩"));
    }

}
