package com.example.springBootRabbitMq.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
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

    @Value("${rabbit.routing.json.key}")
    private  String jsonKey;

    @Value("${rabbit.exchange.name}")
    private String exchangeName;

    @Value("${rabbit.queue.json.name}")
    private String jsonQueue;

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Bean
    public Queue queue() {
        return new Queue(queueName);
    }
    @Bean
    public Queue jsonQueue() {
        return new Queue(jsonQueue);
    }


    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Binding binding( ) {
        return BindingBuilder.bind(queue()).to(topicExchange()).with(routingName);
    }
    @Bean
    public Binding jsonBinding() {
        return BindingBuilder.bind(jsonQueue()).to(topicExchange()).with(jsonKey);
    }
    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
}


