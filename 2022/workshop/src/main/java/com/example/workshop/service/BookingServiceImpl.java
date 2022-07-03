package com.example.workshop.service;

import com.example.workshop.domain.Booking;
import com.example.workshop.domain.Facility;
import com.example.workshop.domain.Member;
import com.example.workshop.repo.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

public class BookingServiceImpl implements BookingService {
    @Autowired
    BookingRepository bookingRepository;

    @Override
    public void createBooking(Date fromDate, Date toDate, Facility facility, Member member) {
        Booking b = new Booking(member, facility, fromDate, toDate);
        bookingRepository.saveAndFlush(b);
    }

}
