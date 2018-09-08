package com.melot.kk.eureka.client.consumer.ribbon.service;

import com.melot.kk.eureka.client.consumer.ribbon.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * UserService5，缓存的使用方式演示
 */
@Component
public class UserService5 {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 根据用户ID获取用户信息
     * 通过添加@CacheResult开启缓存功能，缓存的KEY会使用所有参数
     * @param userId 用户ID
     * @return
     */
    @HystrixCommand
    @CacheResult
    public User getUser(int userId) {
        return restTemplate.getForObject("http://eureka-client-provider/user/getUser?userId={1}", User.class, userId);
    }

    /**
     * 根据用户ID获取用户信息
     * 通过cacheKeyMethod可以指定缓存Key生成的函数
     * @param userId
     * @return
     */
    @HystrixCommand
    @CacheResult(cacheKeyMethod = "getCacheKey")
    public User getUserWithCacheKeyMethod(int userId) {
        return restTemplate.getForObject("http://eureka-client-provider/user/getUser?userId={1}", User.class, userId);
    }

    private int getCacheKey(int userId) {
        return userId;
    }

    /**
     * 根据用户ID获取用户信息
     * 通过@CacheKey注解在方法参数中指定用于组装缓存Key的元素
     * @param userId
     * @return
     */
    @HystrixCommand
    @CacheResult
    public User getUserWithCacheKey(@CacheKey("userId") int userId) {
        return restTemplate.getForObject("http://eureka-client-provider/user/getUser?userId={1}", User.class, userId);
    }

    @HystrixCommand
    @CacheRemove(commandKey = "getUser")
    public void update(@CacheKey("userId") int userId) {
        User badUser = new User();
        badUser.setUserId(99999);
        badUser.setNickname("bad user");
        restTemplate.postForObject("http://eureka-client-provider/user/getUser?userId={1}", badUser, User.class);
    }
}
