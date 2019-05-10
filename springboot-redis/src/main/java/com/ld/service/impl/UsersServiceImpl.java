package com.ld.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.ld.entity.Users;
import com.ld.mapper.UsersDao;
import com.ld.service.UsersService;

/**
 * 具体参考 https://blog.csdn.net/dreamhai/article/details/80642010
 *
 * @Cacheable 标注在类上表示所有的方法都支持缓存，标注在方法上表示该方法支持缓存
 * 对于一个支持缓存的方法，Spring会在其被调用后将其返回值缓存起来，以保证下次利用同样的参数来执行该方法时可以直接从缓存中获取结果，而不需要再次执行该方法
 * @Cacheable 标注的方法，Spring在每次执行前都会检查Cache中是否存在相同key的缓存元素，
 * 如果存在就不再执行该方法，而是直接从缓存中获取结果进行返回，否则才会执行并将返回结果存入指定的缓存中。
 * @CacheEvict 是用来标注在需要清除缓存元素的方法或类上的
 */
@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersDao usersDao;

    @Autowired
    private RedisTemplate<Object, Users> userRedisTemplate;

    @Override
    @Cacheable(value = "users")
    public List<Users> findAll() {
        System.out.println("查询全部用户。。。。。。。。");
        List<Users> findAll = usersDao.findAllUser();
        ListOperations<Object, Users> opsForList = userRedisTemplate.opsForList();
        boolean empty = opsForList.range("users", 0, -1).isEmpty();
        System.out.println(empty);
        //根据key获取过期时间
        userRedisTemplate.getExpire("users");
        //根据key获取过期时间并换算成指定单位
        userRedisTemplate.getExpire("users",TimeUnit.SECONDS);
        if (empty == true) {
            System.out.println("模板插入");
            // 选择右边插入，这样就是正序
            opsForList.rightPushAll("users", findAll);
            //设置过期时间
            // key: 存放键
            // timeout:10000 单位是根据后面这个参数来转换的 SECONDS MILLISECONDS DAYS HOURS MINUTES
            // unit:好像是固定的 TimeUnit.MILLISECONDS
            userRedisTemplate.expire("users", 10000, TimeUnit.MILLISECONDS);
        }
        return findAll;
    }

    @Override
    @Cacheable(value = "user", key = "#id")
    public Users findById(Integer id) {
        System.out.println("根据id查询用户。。。。。。。。");
        return usersDao.findById(id);
    }

    @Override
    @CachePut(value = "user", key = "#users.id")
    public int updateUser(Users users) {
        System.out.println("更新用户。。。。。。。");
        return usersDao.updateUser(users);
    }

    @Override
    @CacheEvict(value = "user")
    public int deleteUser(Integer id) {
        System.out.println("删除用户。。。。。。。。");
//		usersDao.deleteUser(id);
        return 0;
    }

}
