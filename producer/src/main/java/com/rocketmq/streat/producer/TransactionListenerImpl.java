package com.rocketmq.streat.producer;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

@RocketMQTransactionListener(txProducerGroup = "transaction-producer-group")
public class TransactionListenerImpl implements RocketMQLocalTransactionListener {


    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object o) {


        Object num = msg.getHeaders().get("test");

        if ("1".equals(num)) {
            System.out.println("executer: " + msg.getPayload() + " unknown");
            return RocketMQLocalTransactionState.UNKNOWN;
        } else if ("2".equals(num)) {
            System.out.println("executer: " + msg.getPayload() + " rollback");
            return RocketMQLocalTransactionState.ROLLBACK;
        }
        System.out.println("executer: " + msg.getPayload() + " commit");
        return RocketMQLocalTransactionState.COMMIT;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        System.out.println("check: " + message.getPayload());
        return RocketMQLocalTransactionState.COMMIT;
    }
}
