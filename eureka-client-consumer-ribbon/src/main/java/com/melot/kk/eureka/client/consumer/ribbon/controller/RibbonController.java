package com.melot.kk.eureka.client.consumer.ribbon.controller;

import com.melot.kk.eureka.client.consumer.ribbon.service.NumberService;
import com.melot.kk.eureka.client.consumer.ribbon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ribbon")
public class RibbonController {

    @Autowired
    NumberService numberService;

    @Autowired
    UserService userService;

    @GetMapping("/getResult")
    public Map<String, Object> getResult(@RequestParam(value = "userId") int userId) {
        Map<String, Object> map = new HashMap<>();

        map.put("score", numberService.getRandomNumber());
        map.put("user", userService.getUser(userId));

        return map;
    }
}
