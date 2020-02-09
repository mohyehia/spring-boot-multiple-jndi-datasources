package com.mohyehia.jndi.user.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(schema = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
