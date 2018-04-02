package com.turvo.abcbanking.consumer;

import com.turvo.abcbanking.counter.CounterFactory;
import com.turvo.abcbanking.counter.Receiver;
import com.turvo.abcbanking.enums.ServicesOffered;
import com.turvo.abcbanking.model.Counter;
import com.turvo.abcbanking.service.TokenManagementService;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumerBuilder {

    @Autowired
    ConnectionFactory connectionFactory;

    public void build(Counter counter, TokenManagementService tokenManagementService) {

        Receiver receivingCounter = null;

        CounterFactory factory = new CounterFactory();

        String counterName = counter.getCounterName();

        String counterType = counter.getAccountType().toString();

        receivingCounter = factory.createCounterInstance(counter, tokenManagementService);

        for (ServicesOffered operation : counter.getServicesOffered()) {

            System.out.println(operation);
            // Queue creation and binding of the rabbitmq consumer to the specific queue
            new RabbitMQConsumer(counterName, operation + "-" + counterType + "-key" + "-" + counter.getBranch().getId(),
                    operation + "-" + counterType + "-queue" + "-" + counter.getBranch().getId(), connectionFactory, receivingCounter);
        }

    }

}
