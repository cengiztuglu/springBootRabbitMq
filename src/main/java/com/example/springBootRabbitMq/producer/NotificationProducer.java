package com.example.springBootRabbitMq.producer;

import com.example.springBootRabbitMq.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class NotificationProducer {

    @Value("${rabbit.routing.name}")
    private String routingName;

    @Value("${rabbit.exchange.name}")
    private String exchangeName;

    @Value("${rabbit.routing.json.key}")
    private String jsonKey;

    private  static final Logger LOGGER= LoggerFactory.getLogger(NotificationProducer.class);
    private RabbitTemplate rabbitTemplate;
    public NotificationProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }



    public void sendJsonMessage(User user) {
     LOGGER.info(String.format("jsonMessage sent ->%s",user.toString()));
     rabbitTemplate.convertAndSend(exchangeName,routingName,user);
    }
}
