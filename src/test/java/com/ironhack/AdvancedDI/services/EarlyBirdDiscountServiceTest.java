package com.ironhack.AdvancedDI.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EarlyBirdDiscountServiceTest {

    private LocalDate event;
    private LocalDate booking;
    private LocalDate passedDate;
    private EarlyBirdDiscountService earlyBirdDiscountService;

    @BeforeEach
    void setUp() {
        event = LocalDate.parse("2025-07-30");
        booking = LocalDate.parse("2025-05-30");
        passedDate = LocalDate.parse("2025-07-15");
        earlyBirdDiscountService = new EarlyBirdDiscountService();

    }

    @Test
    @DisplayName("Test EarlyBirdDiscountService more than 30 days")
    void testEarlyBirdDiscountServiceMoreThan30Days() {
        String discount = earlyBirdDiscountService.applyDiscount(event, booking);
        assertEquals("15% Discount applied!", discount);
    }

    @Test
    @DisplayName("Test EarlyBirdDiscountService less than 30 days")
    void testEarlyBirdDiscountServiceLessThan30Days() {
        String discount = earlyBirdDiscountService.applyDiscount(event, passedDate);
        assertEquals("No discount available.", discount);
    }


    // Edge Cases
    @Test
    @DisplayName("Test EarlyBirdDiscountService - Same day")
    void testEarlyBirdDiscountServiceSameDay() {
        String discount = earlyBirdDiscountService.applyDiscount(event, event);
        assertEquals("No discount available.", discount);
    }

    @Test
    @DisplayName("Test EarlyBirdDiscountService in 30 days")
    void testEarlyBirdDiscountServiceSameDayPlus30Days() {
        String discount = earlyBirdDiscountService.applyDiscount(event, event.plusDays(30));
        assertEquals("No discount available.", discount);
    }

    @Test
    @DisplayName("Test EarlyBirdDiscountService in 31 days")
    void testEarlyBirdDiscountServiceSameDayPlus31Days() {
        String discount = earlyBirdDiscountService.applyDiscount(event.plusDays(31), event);
        assertEquals("15% Discount applied!", discount);
    }
}