package com.melot.kk.eureka.client.consumer.feign.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "eureka-client-provider")
public interface NumberService {

    @GetMapping("/number/getRandomNumber")
    int getRandomNumber();
}
