package com.prs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prs.pojos.Booking;
import com.prs.pojos.BookingStatus;
import com.prs.service.BookingService;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    
    @PostMapping("/create")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        Booking savedBooking = bookingService.createBooking(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBooking);
    }

    @GetMapping("/pending")
    public ResponseEntity<List<Booking>> getPendingBookings() {
        return ResponseEntity.ok(bookingService.getPendingBookings());
    }

    @PutMapping("/approve/{bookingId}")
//    @PreAuthorize("LANDLORD")
    public ResponseEntity<String> approveBooking(@PathVariable Long bookingId) {
    	System.out.println(bookingId);
        bookingService.updateBookingStatus(bookingId, BookingStatus.APPROVED);
        return ResponseEntity.ok("Booking Approved Successfully");
    }

    @PutMapping("/{bookingId}/reject")
    public ResponseEntity<String> rejectBooking(@PathVariable Long bookingId) {
        bookingService.updateBookingStatus(bookingId, BookingStatus.REJECTED);
        return ResponseEntity.ok("Booking Rejected Successfully");
    }
    
    @GetMapping("/{tenantId}")
    public ResponseEntity<List<Booking>> getRequestedBookings(@PathVariable Long tenantId) {
        return ResponseEntity.ok(bookingService.getBookingsRequests(tenantId));
    }
    
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }
}
