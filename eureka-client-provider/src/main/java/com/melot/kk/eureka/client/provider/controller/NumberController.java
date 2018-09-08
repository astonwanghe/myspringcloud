package com.melot.kk.eureka.client.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/number")
public class NumberController {

    @GetMapping("/getRandomNumber")
    public int getRandomNumber() throws InterruptedException {
        // 随机睡眠0-3000毫秒, 当>2000毫秒时可以触发断路器
        Thread.sleep(new Random().nextInt(3000));
        return new Random().nextInt(100);
    }
}
