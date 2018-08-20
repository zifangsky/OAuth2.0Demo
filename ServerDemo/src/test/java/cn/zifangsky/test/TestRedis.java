package cn.zifangsky.test;

import cn.zifangsky.mapper.UserMapper;
import cn.zifangsky.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundKeyOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.concurrent.TimeUnit;

/**
 * 测试Redis的基本操作
 *
 * @author zifangsky
 * @date 2018/7/30
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TestRedis {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;



    /**
     * ValueOperations、SetOperations、ZSetOperations
     * ListOperations、HashOperations
     */
    @Test
    public void testSaveAndGet(){
        User user = userMapper.selectByPrimaryKey(1);
        ValueOperations<String, Object> valueOperation = redisTemplate.opsForValue();
        valueOperation.set("test:user:" + user.getId(), user);

        User result = (User) valueOperation.get("test:user:" + user.getId());
        System.out.println("name: " + result.getUsername());
    }

    @Test
    public void testDelete(){
        redisTemplate.delete("test:user:1");
    }

    /**
     * BoundValueOperations、BoundSetOperations
     * BoundListOperations、BoundSetOperations、BoundHashOperations
     */
    @Test
    public void testBoundOperations(){
        BoundValueOperations<String, Object> boundValueOperations = redisTemplate.boundValueOps("BoundTest");
        //设置值
        boundValueOperations.set("test12345");
        //设置过期时间
        boundValueOperations.expire(100, TimeUnit.SECONDS);
        //重命名Key
//        boundValueOperations.rename("BoundTest123");

        System.out.println("key: " + boundValueOperations.getKey());
        System.out.println(boundValueOperations.get());
        System.out.println("expire: " + boundValueOperations.getExpire());

    }



}
