package com.ironhack.AdvancedDI.config;

import com.ironhack.AdvancedDI.services.EarlyBirdDiscountService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EarlyBirdDiscountConfig {

@Bean
@ConditionalOnProperty(name = "feature.earlybird.enabled", havingValue = "true")
public EarlyBirdDiscountService earlyBirdDiscountService() {
    return new EarlyBirdDiscountService();
}

}
