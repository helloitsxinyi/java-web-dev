package com.example.workshop.repo;

import com.example.workshop.domain.Booking;
import com.example.workshop.domain.Facility;
import com.example.workshop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Date;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    @Query("select b from Booking b where :date between b.fromDate and b.toDate and b.facility.facilityId = :fid")
    public ArrayList<Booking> checkBookingAvailable(@Param("date") Date date, @Param("fid") String fid);
}
