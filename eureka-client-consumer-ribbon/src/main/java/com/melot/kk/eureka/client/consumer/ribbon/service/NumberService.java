package com.melot.kk.eureka.client.consumer.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

@Component
public class NumberService {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 获取一个随机数(同步执行方式)
     * @return
     */
    @HystrixCommand(fallbackMethod = "getRandomNumberFallback")
    public int getRandomNumber() {
        return restTemplate.getForObject("http://eureka-client-provider/number/getRandomNumber", int.class);
    }

    /**
     * 断路后的回调方法, 回调方法返回的类型必须与原方法一致
     * @return
     */
    private int getRandomNumberFallback() {
        System.out.println("NumberService.getRandomNumber, we are in fall back.");
        return -1;
    }

    /**
     * 获取一个随机数(异步执行方式)
     * @return
     */
    @HystrixCommand
    public Future<Integer> getRandomNumberAsync() {
        return new AsyncResult<Integer>() {
            @Override
            public Integer invoke() {
                return restTemplate.getForObject("http://eureka-client-provider/number/getRandomNumber", int.class);
            }
        };
    }
}
