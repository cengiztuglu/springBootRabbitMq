package com.example.springBootRabbitMq.listener;

import com.example.springBootRabbitMq.model.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {
   @RabbitListener(queues = {"${rabbit.queue.name}"})
   public void handleMessage(Notification notification)
   {
      System.out.println("Message received..");
      System.out.println(notification.toString());
   }
}
