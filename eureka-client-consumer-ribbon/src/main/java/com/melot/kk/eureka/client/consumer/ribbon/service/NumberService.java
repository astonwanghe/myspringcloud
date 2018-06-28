package com.melot.kk.eureka.client.consumer.ribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NumberService {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 获取一个随机数
     * @return
     */
    public int getRandomNumber() {
        return restTemplate.getForObject("http://eureka-client-provider/number/getRandomNumber", int.class);
    }
}
