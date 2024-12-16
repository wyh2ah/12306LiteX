package com.wxy.springbackend.controller;

import com.wxy.springbackend.model.Booking;
import com.wxy.springbackend.model.BookingResponse;
import com.wxy.springbackend.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // POST /api/bookings/book
    // Body (JSON): { "userId": 123, "pathId": 456, "departureStationName": "StationA", "arrivalStationName": "StationB", "departureTime": "2024-12-23 11:00:00", "arrivalTime": "2024-12-23 12:00:00", "seatLevel": "A", "price": 150.50 }
    @PostMapping("/booking")
    public Map<String, Object> bookTicket(@RequestBody Booking booking) {
        Map<String, Object> response = new HashMap<>();
        try {
            BookingResponse bookingResponse = bookingService.bookTicket(booking);

            Map<String, Object> infoMap = new HashMap<>();
            infoMap.put("invoiceId", bookingResponse.getInvoiceId());
            infoMap.put("ticketId", bookingResponse.getTicketId());
            infoMap.put("validState", bookingResponse.getValidState());
            infoMap.put("paymentState", bookingResponse.getPaymentState());

            response.put("Information", infoMap);
            response.put("Status", "Success");

            return response;
        } catch (Exception e) {
            response.put("Information", null);  // or omit if you prefer
            response.put("Status", e.getMessage());
            return response;
        }
    }
}
