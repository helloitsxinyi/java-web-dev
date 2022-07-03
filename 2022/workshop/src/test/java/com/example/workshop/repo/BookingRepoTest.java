package com.example.workshop.repo;

import com.example.workshop.WorkshopApplication;
import com.example.workshop.domain.Booking;
import com.example.workshop.domain.Facility;
import com.example.workshop.domain.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

@SpringBootTest(classes= WorkshopApplication.class)
public class BookingRepoTest {

    @Autowired
    FacilityRepository frepo;

    @Autowired
    MemberRepository mrepo;

    @Autowired
    BookingRepository brepo;


    @Test
    @Order(1)
    public void createBookingTest() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        Facility f = new Facility("ISS", "lesson");
        Member m = new Member("John","Leny", "Tan");
        Booking b = new Booking(m,f,formatter.parse("03-08-2022"), formatter.parse("10-08-2022"), "test booking");
        frepo.saveAndFlush(f);
        mrepo.saveAndFlush(m);
        brepo.saveAndFlush(b);
        Assertions.assertNotNull(b);
    }

    @Test
    @Order(2)
    public void testRepoCreateMethod() {

    }

}
