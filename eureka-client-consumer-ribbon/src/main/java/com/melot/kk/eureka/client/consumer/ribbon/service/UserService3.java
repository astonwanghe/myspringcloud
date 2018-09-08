package com.melot.kk.eureka.client.consumer.ribbon.service;

import com.melot.kk.eureka.client.consumer.ribbon.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

@Component
public class UserService3 {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 获取用户信息(订阅模式, 可以返回多个结果)
     * 可以通过observableExecutionMode来控制是observe还是toObserve的执行方式
     * @param userId
     * @return
     */
    @HystrixCommand(observableExecutionMode = ObservableExecutionMode.EAGER)
    public Observable<User> getUserObservable(int userId) {
        return Observable.create(subscriber -> {
            try {
                if (!subscriber.isUnsubscribed()) {
                    User user = restTemplate.getForObject("http://eureka-client-provider/user/getUser?userId={1}", User.class, userId);
                    subscriber.onNext(user);
                    subscriber.onCompleted();
                }
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }
}
