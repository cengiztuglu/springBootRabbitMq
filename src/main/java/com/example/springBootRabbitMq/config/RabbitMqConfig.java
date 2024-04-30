package com.example.springBootRabbitMq.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Value("${rabbit.queue.name}")
    private String queueName;

    @Value("${rabbit.routing.name}")
    private String routingName;
    @Value("${rabbit.exchange.name}")

    private String exchangeName;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Bean
    public Queue queue() {
        return new Queue(queueName);
    }

    @Bean
    public TopicExchange directExchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Binding binding(final Queue queue, final TopicExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with(routingName);
    }

}
