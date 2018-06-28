package com.melot.kk.eureka.client.consumer.ribbon.service;

import com.melot.kk.eureka.client.consumer.ribbon.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserService {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    public User getUser(int userId) {
        return restTemplate.getForObject("http://eureka-client-provider/user/getUser?userId={1}", User.class, userId);
    }
}
