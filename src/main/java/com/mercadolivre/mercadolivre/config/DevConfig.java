package com.mercadolivre.mercadolivre.config;

import com.mercadolivre.mercadolivre.email.EmailService;
import com.mercadolivre.mercadolivre.email.SimulaEmail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Bean
    public EmailService emailService() {
        return new SimulaEmail();
    }
}
