package com.mohyehia.jndi.booking.service.implementation;

import com.mohyehia.jndi.booking.dao.BookingDAO;
import com.mohyehia.jndi.booking.entity.Booking;
import com.mohyehia.jndi.booking.service.framework.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    private BookingDAO bookingDAO;

    @Autowired
    public BookingServiceImpl(BookingDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    @Override
    public List<Booking> findAll() {
        return bookingDAO.findAll();
    }

    @Override
    public List<Booking> findByUserId(long userId) {
        return bookingDAO.findByUserId(userId);
    }

    @Override
    public Booking findById(long id) {
        return bookingDAO.findById(id).orElse(null);
    }

    @Override
    public Booking save(Booking booking) {
        return bookingDAO.save(booking);
    }

    @Override
    public void deleteBooking(long id) {
        bookingDAO.deleteById(id);
    }
}
