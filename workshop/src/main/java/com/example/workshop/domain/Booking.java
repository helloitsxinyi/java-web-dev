package com.example.workshop.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Data
@ToString
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;

    @OneToOne
    private Facility facility;

    @OneToOne
    private Member member;
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date fromDate;
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date toDate;
    private String description;

    public Booking(Integer bookingId, @NotEmpty Member member, @NotEmpty Facility facility,
                   Date fromDate, Date toDate, String description) {
        this.bookingId = bookingId;
        this.facility = facility;
        this.member = member;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.description = description;
    }

    public Booking(@NotEmpty Member member, @NotEmpty Facility facility,
                   @FutureOrPresent Date fromDate, @FutureOrPresent Date toDate) {
        this.facility = facility;
        this.member = member;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }


    public Booking(@NotEmpty Member member, @NotEmpty Facility facility,
                   @FutureOrPresent Date fromDate, @FutureOrPresent Date toDate, String description) {
        this.facility = facility;
        this.member = member;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.description = description;
    }
}
