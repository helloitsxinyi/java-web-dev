package com.example.workshop.service;

import com.example.workshop.domain.Facility;
import com.example.workshop.domain.Member;

import java.util.Date;

public interface BookingService {
    public void createBooking(Date fromDate, Date toDate, Facility facility, Member member);

}
