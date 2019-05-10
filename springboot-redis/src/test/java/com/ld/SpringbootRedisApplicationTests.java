package com.ld;

import com.ld.entity.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisApplicationTests {

    @Resource
    private Users users;

    //这里使用的自己定义的com.ld.config.RedisConfig
    @Resource
    private RedisTemplate<Object, Users> userRedisTemplate;

    /**
     * 测试使用模板进行插入，插入的值不会是二进制字节码
     */
    @Test
    public void contextLoads() {
        Users user = new Users();
        user.setId(2);
        user.setUserPassword("123456");
        user.setUserName("张三");
        userRedisTemplate.opsForValue().set("user1", user);
    }

    /**
     * 测试使用模板获取
     */
    @Test
    public void contextGets1() {
        Users users2 = userRedisTemplate.opsForValue().get("user1");
        System.out.println(users2);
    }

    /**
     * 测试使用模板获取存储为list集合的值
     */
    @Test
    public void contextGetList() {
        ListOperations<Object, Users> opsForList = userRedisTemplate.opsForList();
        List<Users> range = opsForList.range("users", 0, -1);
        System.out.println(range);
    }

}
