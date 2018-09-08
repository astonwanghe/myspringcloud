package com.melot.kk.eureka.client.consumer.ribbon.controller;

import com.melot.kk.eureka.client.consumer.ribbon.model.User;
import com.melot.kk.eureka.client.consumer.ribbon.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/ribbon")
public class RibbonController {

    @Autowired
    NumberService numberService;

    @Autowired
    UserService3 userService3;

    @Autowired
    UserService1 userService1;

    @Autowired
    UserService2 userService2;

    @Autowired
    UserService4 userService4;

    @Autowired
    UserService5 userService5;

    @GetMapping("getResult1")
    public Map<String, Object> getResult1(@RequestParam(value = "userId") int userId) {
        Map<String, Object> map = new HashMap<>();

        map.put("score", numberService.getRandomNumber());
        map.put("user", userService1.getUser(userId));

        return map;
    }

    @GetMapping("getResult2")
    public Map<String, Object> getResult2(@RequestParam(value = "userId") int userId) throws ExecutionException, InterruptedException {
        Map<String, Object> map = new HashMap<>();

        Future<User> userAsync = userService2.getUserAsync(userId);
        map.put("score", numberService.getRandomNumber());
        map.put("user", userService2.getUser(userId));
        map.put("user2", userAsync.get());

        return map;
    }

    @GetMapping("getResult4")
    public Map<String, Object> getResult4(@RequestParam(value = "userId") int userId) throws ExecutionException, InterruptedException {
        Map<String, Object> map = new HashMap<>();

        map.put("score", numberService.getRandomNumber());
        map.put("user", userService4.getUserWithException(userId));

        return map;
    }

    @GetMapping("getResult5")
    public Map<String, Object> getResult5(@RequestParam(value = "userId") int userId) throws ExecutionException, InterruptedException {
        Map<String, Object> map = new HashMap<>();

        map.put("score", numberService.getRandomNumber());
        map.put("user", userService5.getUser(userId));
        map.put("user2", userService5.getUserWithCacheKey(userId));
        map.put("user3", userService5.getUserWithCacheKeyMethod(userId));

        return map;
    }
}
