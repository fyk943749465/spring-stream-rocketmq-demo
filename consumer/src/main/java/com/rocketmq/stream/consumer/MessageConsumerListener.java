package com.rocketmq.stream.consumer;

import com.rocketmq.stream.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Component
@Slf4j
public class MessageConsumerListener {

    @Bean
    Consumer<Order> backtracking() {
        return order -> {
            log.info("receive message:"+order.getId() + ":" + order.getDesc());
        };
    }

    @Bean
    Consumer<String> transaction() {
        return msg -> {
            log.info("receive msg:" + msg);
        };
    }
}
