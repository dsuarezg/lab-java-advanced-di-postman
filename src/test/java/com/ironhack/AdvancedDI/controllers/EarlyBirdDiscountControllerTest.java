package com.ironhack.AdvancedDI.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EarlyBirdDiscountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("${feature.earlybird.enabled:true}")
    private boolean earlyBirdEnabled;

    @Test
    void getEarlyBirdDiscount_ServiceAvailable() throws Exception {
        if(earlyBirdEnabled){
        mockMvc.perform(get("/api/discount")
                        .param("eventDate", "2025-07-30")
                        .param("bookingDate", "2025-06-15"))
                .andExpect(status().isOk())
                .andExpect(content().string("15% Discount applied!"));
    }else{
            System.out.println("Early Bird Discount feature is disabled.");
        }
}
    @Test
    void getEarlyBirdDiscount_ServiceUnavailable() throws Exception {
        if(!earlyBirdEnabled){
        mockMvc.perform(get("/api/discount")
                        .param("eventDate", "2025-07-30")
                        .param("bookingDate", "2025-07-15"))
                .andExpect(status().isServiceUnavailable())
                .andExpect(content().string("Discount service not available."));
    }else{
            System.out.println("Early Bird Discount feature is enabled.");
        }
    }
}