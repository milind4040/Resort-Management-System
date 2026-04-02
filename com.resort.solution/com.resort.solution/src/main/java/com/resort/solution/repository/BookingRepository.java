package com.resort.solution.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resort.solution.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
	List<Booking> findByUser_UserId(Integer userId);
	List<Booking> findByResort_ResortId(Integer resortId);
}
