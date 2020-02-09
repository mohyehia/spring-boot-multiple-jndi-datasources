package com.mohyehia.jndi.booking.controller;

import com.mohyehia.jndi.booking.entity.Booking;
import com.mohyehia.jndi.booking.service.framework.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingController {
    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> retrieveAllBookings(){
        return new ResponseEntity<>(bookingService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/bookings/{bookingId}")
    public ResponseEntity<Booking> retrieveBooking(@PathVariable("bookingId") long bookingId){
        Booking booking = bookingService.findById(bookingId);
        if(booking == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(booking, HttpStatus.FOUND);
    }

    @GetMapping("/{userId}/bookings")
    public ResponseEntity<List<Booking>> retrieveUserBookings(@PathVariable("userId") long userId){
        return new ResponseEntity<>(bookingService.findByUserId(userId), HttpStatus.OK);
    }

    @PostMapping("/{userId}/bookings")
    public ResponseEntity<Booking> saveBookingForUser(@PathVariable("userId") long userId, @RequestBody Booking booking){
        booking.setUserId(userId);
        booking = bookingService.save(booking);
        if(booking == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }

    @DeleteMapping("/bookings/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable("bookingId") long bookingId){
        bookingService.deleteBooking(bookingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
