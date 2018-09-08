package com.melot.kk.eureka.client.consumer.ribbon.service;

import com.melot.kk.eureka.client.consumer.ribbon.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

/**
 * UserService2, 包含基本熔断机制，定义了服务降级方法,
 * 提供同步方式和异步方式两种实现
 */
@Component
public class UserService2 {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @HystrixCommand(fallbackMethod = "getUserFallBack")
    public User getUser(int userId) {
        return restTemplate.getForObject("http://eureka-client-provider/user/getUser?userId={1}", User.class, userId);
    }

    /**
     * 断路后的回调方法, 回调方法返回的类型必须与原方法一致
     * @return
     */
    public User getUserFallBack(int userId) {
        return null;
    }

    /**
     * 获取用户信息(异步方式)
     * @param userId
     * @return
     */
    @HystrixCommand(fallbackMethod = "getUserFallBack")
    public Future<User> getUserAsync(int userId) {
        return new AsyncResult<User>() {
            @Override
            public User invoke() {
                return restTemplate.getForObject("http://eureka-client-provider/user/getUser?userId={1}", User.class, userId);
            }
        };
    }
}
