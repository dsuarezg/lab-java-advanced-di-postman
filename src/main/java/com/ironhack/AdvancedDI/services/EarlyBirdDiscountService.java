package com.ironhack.AdvancedDI.services;

import java.time.LocalDate;

public class EarlyBirdDiscountService {

    public String applyDiscount(LocalDate eventDate, LocalDate bookingDate) {

        if (eventDate.isAfter(bookingDate.plusDays(30))) {
            return "15% Discount applied!";
        } else {
            return "No discount available.";
        }
    }

}
