package com.rocketmq.streat.producer;

import com.rocketmq.streat.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageProducer {

    @Autowired
    private StreamBridge streamBridge;

    @GetMapping("/produce")
    public void produceMsg() {

        //for (int i = 0; i < 10; ++i) {
            Order order = Order.builder().id((long) 0).desc("test" + 0).build();
            Message<Order> message = MessageBuilder.withPayload(order)
                    .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                    .build();

            streamBridge.send("backtracking-out-0", message);
        //}
    }

    @GetMapping("/send_transaction")
    public void sendTransaction() {

        String msg = "这是一条事务消息";
        Integer num = 0;

        Message<String> message = MessageBuilder.withPayload(msg)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .setHeader("test", String.valueOf(num))
                        .build();

        streamBridge.send("transaction-out-0", message);

    }

}
