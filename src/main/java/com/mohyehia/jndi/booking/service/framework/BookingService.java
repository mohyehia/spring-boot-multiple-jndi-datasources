package com.mohyehia.jndi.booking.service.framework;

import com.mohyehia.jndi.booking.entity.Booking;

import java.util.List;

public interface BookingService {
    List<Booking> findAll();
    List<Booking> findByUserId(long userId);
    Booking findById(long id);
    Booking save(Booking booking);
    void deleteBooking(long id);
}
