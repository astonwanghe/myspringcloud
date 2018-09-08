package com.melot.kk.eureka.client.consumer.ribbon.service;

import com.melot.kk.eureka.client.consumer.ribbon.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * UserService1，ribbon访问的基本形态，没有包含熔断机制
 */
@Component
public class UserService1 {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 根据用户ID获取用户信息
     * @param userId 用户ID
     * @return
     */
    public User getUser(int userId) {
        return restTemplate.getForObject("http://eureka-client-provider/user/getUser?userId={1}", User.class, userId);
    }
}
