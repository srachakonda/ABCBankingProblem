package com.turvo.abcbanking.consumer;

import com.turvo.abcbanking.counter.Receiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;


public class RabbitMQConsumer {

    private final ConnectionFactory connectionFactory;

    /**
     * The routing key is the message type that the consumer wants to read from the queue.
     */
    private String routingKey;

    /**
     * The queue name is the name of the queue that is shared between a pool of
     * consumers for load balancing the messages within the same service.
     */
    private String queueName;

    private String name;

    public RabbitMQConsumer(String consumerName, String routingKey, String queueName, ConnectionFactory connectionFactory, Receiver receiver) {
        this.name = consumerName;
        this.routingKey = routingKey;
        this.queueName = queueName;
        this.connectionFactory = connectionFactory;

        initContainer(receiver);
    }

    public MessageConverter jsonMessageConverter2() {
        return new Jackson2JsonMessageConverter();
    }

    private void initContainer(Receiver receiver) {
        // set up the queue, exchange, binding on the broker
        RabbitAdmin admin = new RabbitAdmin(connectionFactory);
        Queue queue = new Queue(queueName);
        admin.declareQueue(queue);
        TopicExchange exchange = new TopicExchange("tokens-exchange");
        admin.declareExchange(exchange);
        admin.declareBinding(BindingBuilder.bind(queue).to(exchange).with(routingKey));

        // set up the listener and container
        SimpleMessageListenerContainer container =
                new SimpleMessageListenerContainer(connectionFactory);

        MessageListenerAdapter adapter = new MessageListenerAdapter(receiver, "receiveToken");
        adapter.setMessageConverter(jsonMessageConverter2());
        container.setMessageListener(adapter);
        container.setQueueNames(queueName);
        container.start();
    }

}
