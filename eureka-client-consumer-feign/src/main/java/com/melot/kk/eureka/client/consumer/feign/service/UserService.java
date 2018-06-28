package com.melot.kk.eureka.client.consumer.feign.service;

import com.melot.kk.eureka.client.consumer.feign.model.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "eureka-client-provider")
public interface UserService {

    @GetMapping("/user/getUser")
    User getUser(@RequestParam(value = "userId") int userId);
}
