package com.italoghiele.projetointegrador.service.impl;

import com.italoghiele.projetointegrador.domain.User;
import com.italoghiele.projetointegrador.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.*;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendNewPasswordEmail(User user, String newPas){
        SimpleMailMessage simpleMailMessage = prepareNewPasswordEmail(user, newPas);
        sendEmail(simpleMailMessage);
    }

    protected SimpleMailMessage prepareNewPasswordEmail(User customer, String newPas){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(customer.getEmail());
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setSubject("Solicitação de nova senha");
        simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
        simpleMailMessage.setText("Nova senha: " + newPas);

        return simpleMailMessage;
    }
}
