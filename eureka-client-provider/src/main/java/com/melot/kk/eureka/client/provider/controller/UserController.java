package com.melot.kk.eureka.client.provider.controller;

import com.melot.kk.eureka.client.provider.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/getUser")
    public User getUser(@RequestParam(value = "userId") int userId) {
        User user = new User();
        user.setUserId(userId);
        user.setNickname("User_" + userId);
        return user;
    }
}
