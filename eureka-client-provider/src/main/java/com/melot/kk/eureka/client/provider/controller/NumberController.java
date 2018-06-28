package com.melot.kk.eureka.client.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/number")
public class NumberController {

    @GetMapping("/getRandomNumber")
    public int getRandomNumber() {
        return new Random().nextInt(100);
    }
}
