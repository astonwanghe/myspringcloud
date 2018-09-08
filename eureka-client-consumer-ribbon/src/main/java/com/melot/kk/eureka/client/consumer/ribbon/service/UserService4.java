package com.melot.kk.eureka.client.consumer.ribbon.service;

import com.melot.kk.eureka.client.consumer.ribbon.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * UserService4，对异常处理方式进行了演示
 */
@Component
public class UserService4 {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 获取用户信息，通过配置ignoreExceptions，可以让Hystrix忽略指定的异常，
     * 也就是当此方法抛出ignoreExceptions里指定的异常时，不会触发服务降级处理
     * @param userId
     * @return
     */
    @HystrixCommand(ignoreExceptions = {HystrixBadRequestException.class})
    public User getUser(int userId) {
        return restTemplate.getForObject("http://eureka-client-provider/user/getUser?userId={1}", User.class, userId);
    }

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @HystrixCommand(fallbackMethod = "getUserWithExceptionFallBack")
    public User getUserWithException(int userId) {
        throw new RuntimeException("get user failed");
    }

    /**
     * 熔断的回路方法，这里面加入了Throwable参数，可以在此方法内部获取服务降级的具体异常内容
     * @param userId
     * @param e
     * @return
     */
    private User getUserWithExceptionFallBack(int userId, Throwable e) {
        e.printStackTrace();
        return null;
    }
}
