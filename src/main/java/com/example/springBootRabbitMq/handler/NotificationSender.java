package com.example.springBootRabbitMq.handler;

import com.example.springBootRabbitMq.model.Notification;
import com.example.springBootRabbitMq.producer.NotificationProducer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class NotificationSender {
    @Autowired
    private NotificationProducer producer;
    public void getThread(){
        Thread thread=new Thread(()->{
            while (true){
                Notification notification=new Notification();
                notification.setNotificationId(UUID.randomUUID().toString());
                notification.setCreatedAt(new Date());
                notification.setMessage("Hello My Message");
                notification.setSeen(false);
                producer.sendToQueue(notification);
                try {
                    Thread.sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });thread.setName("Notifacition Sender");
        thread.start();
    }
    @PostConstruct
    public void init()
    {
        getThread();
    }
}
