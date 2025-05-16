package com.ironhack.AdvancedDI.controllers;

import com.ironhack.AdvancedDI.services.EarlyBirdDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/discount")
public class EarlyBirdDiscountController {


    private final Optional<EarlyBirdDiscountService> discountService;

    @Autowired(required = false)
    public EarlyBirdDiscountController(Optional<EarlyBirdDiscountService> earlyBirdDiscountService) {
        this.discountService = earlyBirdDiscountService;
    }

    @GetMapping()
    public ResponseEntity<String> getEarlyBirdDiscount(@RequestParam("eventDate") String eventDate,
                                                        @RequestParam("bookingDate") String bookingDate) {
        LocalDate event = LocalDate.parse(eventDate);
        LocalDate booking = LocalDate.parse(bookingDate);

        if (discountService.isPresent()) {
            String discountMessage = discountService.get().applyDiscount(event, booking);
            return ResponseEntity.ok(discountMessage);
        } else {
            return ResponseEntity.status(503).body("Discount service not available.");
        }
    }
}
