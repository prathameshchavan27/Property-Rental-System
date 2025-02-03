package com.prs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prs.pojos.Booking;
import com.prs.pojos.BookingStatus;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByStatus(BookingStatus status);
    List<Booking> findByTenantId(Long tenantId);
}
