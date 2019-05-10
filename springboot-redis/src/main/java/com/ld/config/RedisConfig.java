package com.ld.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.ld.entity.Users;

@Configuration
public class RedisConfig {
	
	/**
	 * 创建一个 RedisTemplate<Object, Users> 泛型Bean
	 * Users:写自己需要转换的类，也可以写 Object
	 * @param redisConnectionFactory
	 * @return RedisTemplate
	 */
	@Bean
	public RedisTemplate<Object, Users> userRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<Object, Users> template = new RedisTemplate<Object, Users>();
		template.setConnectionFactory(redisConnectionFactory);
		Jackson2JsonRedisSerializer<Users> json = new Jackson2JsonRedisSerializer<Users>(Users.class);
		template.setDefaultSerializer(json);
		return template;
	}
}
