package com.example.springBootRabbitMq.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private int id;
    private String  firstName;
    private String  lastName;



}
