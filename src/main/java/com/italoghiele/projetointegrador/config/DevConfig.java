package com.italoghiele.projetointegrador.config;

import com.italoghiele.projetointegrador.service.EmailService;
import com.italoghiele.projetointegrador.service.impl.SmtpEmailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Bean
    public EmailService emailService(){
        return new SmtpEmailServiceImpl();
    }

}
