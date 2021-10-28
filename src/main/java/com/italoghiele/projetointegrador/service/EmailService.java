package com.italoghiele.projetointegrador.service;

import com.italoghiele.projetointegrador.domain.User;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendEmail(SimpleMailMessage simpleMailMessage);

    void sendNewPasswordEmail(User user, String newPass);
}
