package com.resort.solution.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.resort.solution.entity.Booking;
import com.resort.solution.entity.BookingRoom;
import com.resort.solution.entity.BookingService;
import com.resort.solution.service.BookingRoomService;
import com.resort.solution.service.BookingServiceInterface;
import com.resort.solution.service.BookingServiceService;

@RestController
@RequestMapping("/user/bookings")
public class BookingController {

    @Autowired
    private BookingServiceInterface bookingService;

    @Autowired
    private BookingRoomService bookingRoomService;

    @Autowired
    private BookingServiceService bookingServiceService;

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/initiateBooking")
    public ResponseEntity<?> createBooking(@RequestBody Booking booking) {
        try {
            Booking created = bookingService.createBooking(booking);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }


    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/{bookingId}/rooms")
    public ResponseEntity<?> addRoomToBooking( @PathVariable Integer bookingId, @RequestBody BookingRoom bookingRoom) {
        try {
            BookingRoom saved =
                    bookingRoomService.addRoomToBooking(bookingId, bookingRoom);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @DeleteMapping("/rooms/{bookingRoomId}")
    public ResponseEntity<?> removeRoomFromBooking(@PathVariable Integer bookingRoomId) {
        try {
            boolean removed = bookingRoomService.removeRoomFromBooking(bookingRoomId);
            if (!removed) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "Room not found in booking"));
            }
            return ResponseEntity.ok(Map.of("message", "Room removed successfully"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/{bookingId}/services")
    public ResponseEntity<?> addServiceToBooking(
            @PathVariable Integer bookingId,
            @RequestBody BookingService bookingServiceEntity) {

        try {
            BookingService saved = bookingServiceService.addServiceToBooking(bookingId, bookingServiceEntity);

            return ResponseEntity.status(HttpStatus.CREATED).body(saved);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }


    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @DeleteMapping("/services/{bookingServiceId}")
    public ResponseEntity<?> removeServiceFromBooking(@PathVariable Integer bookingServiceId) {
        try {
            boolean removed = bookingServiceService.removeServiceFromBooking(bookingServiceId);
            if (!removed) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "Service not found in booking"));
            }
            return ResponseEntity.ok(Map.of("message", "Service removed successfully"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PutMapping("/{bookingId}/confirm")
    public ResponseEntity<?> confirmBooking(@PathVariable Integer bookingId) {
        try {
            bookingService.confirmBooking(bookingId);
            return ResponseEntity.ok(Map.of("message", "Booking confirmed successfully"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PutMapping("/{bookingId}/cancel")
    public ResponseEntity<?> cancelBooking(@PathVariable Integer bookingId) {
        try {
            bookingService.cancelBooking(bookingId);
            return ResponseEntity.ok(Map.of("message", "Booking cancelled successfully"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PutMapping("/{bookingId}/complete")
    public ResponseEntity<?> completeBooking(@PathVariable Integer bookingId) {
        try {
            bookingService.completeBooking(bookingId);
            return ResponseEntity.ok(Map.of("message", "Booking completed successfully"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{bookingId}")
    public ResponseEntity<?> getBookingById(@PathVariable Integer bookingId) {
        Booking booking = bookingService.getBookingById(bookingId);
        if (booking == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Booking not found"));
        }
        return ResponseEntity.ok(booking);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/user/{userId}")
    public List<Booking> getBookingsByUser(@PathVariable Integer userId) {
        return bookingService.getBookingsByUser(userId);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/resort/{resortId}")
    public List<Booking> getBookingsByResort(@PathVariable Integer resortId) {
        return bookingService.getBookingsByResort(resortId);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/getAllBooking")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }
}



//package com.resort.solution.controller;
//
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.resort.solution.entity.Booking;
//import com.resort.solution.entity.BookingRoom;
//import com.resort.solution.service.BookingRoomService;
//import com.resort.solution.service.BookingServiceInterface;
//
//@RestController
//@RequestMapping("/booking")
//class BookingController {
//	
//	@Autowired
//	private BookingServiceInterface bookingServiceInter;
//	
//	@Autowired
//	private BookingRoomService bookingRoomServ;
//	
//	
//	@PostMapping("/room/bookroom")
//	public BookingRoom bookthisRoom(@RequestBody BookingRoom bookroom) {
//		return bookingRoomServ.addRoomToBooking(bookroom);
//	}
//	
//
//	
//	
//	@PostMapping("/addBooking")
//	public Booking addBoking(@RequestBody Booking booking) {
//		return bookingServiceInter.createBooking(booking);
//	}
//	
//	@PutMapping("/comfirmBooking")
//	public ResponseEntity<?> confirmBooking(@RequestParam Integer bookingId) {
//		boolean confirmed =  bookingServiceInter.confirmBooking(bookingId);
//		if(!confirmed) {
//			return ResponseEntity.ok(Map.of("message" , "Booking not confirmed"));
//		}
//		return ResponseEntity.ok(Map.of("message" , "Booking confirmed"));
//	}
//
//	@PutMapping("/completeBooking")
//	public ResponseEntity<?> completeBooking(@RequestParam Integer bookingId) {
//		boolean confirmed =  bookingServiceInter.completeBooking(bookingId);
//		if(!confirmed) {
//			return ResponseEntity.ok(Map.of("message" , "Booking not complete"));
//		}
//		return ResponseEntity.ok(Map.of("message" , "Booking complete"));
//	}
//	
//	@PutMapping("/cancelBooking")
//	public ResponseEntity<?> cancelBooking(@RequestParam Integer bookingId) {
//		boolean confirmed =  bookingServiceInter.cancelBooking(bookingId);
//		if(!confirmed) {
//			return ResponseEntity.ok(Map.of("message" , "Booking not cancel"));
//		}
//		return ResponseEntity.ok(Map.of("message" , "Booking cancel"));
//	}
//	
//	@GetMapping("/getBookingById")
//	public Booking getBookById(@RequestParam Integer bookingId) {
//		return bookingServiceInter.getBookingById(bookingId);
//	}
//	
//	@GetMapping("/getBookingsByUser")
//	public List<Booking> getBookingsByUserId(@RequestParam Integer userId) {
//		return bookingServiceInter.getBookingsByUser(userId);
//	}
//	
//	@GetMapping("/getBookingsByResort")
//	public List<Booking> getBookingsByResortId(@RequestParam Integer resortId) {
//		return bookingServiceInter.getBookingsByResort(resortId);
//	}
//	
//	@GetMapping("/getAllBooking")
//	public List<Booking> getAllBookings() {
//		return bookingServiceInter.getAllBookings();
//	}
//}
//
//
////•	create booking
////•	confirm/cancel booking
////•	get booking history
