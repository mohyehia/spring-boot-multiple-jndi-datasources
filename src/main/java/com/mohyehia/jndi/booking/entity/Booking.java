package com.mohyehia.jndi.booking.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(schema = "booking")
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long userId;
    private String pickupAddress;
    private String dropAddress;
    private String bookingAmount;
}
