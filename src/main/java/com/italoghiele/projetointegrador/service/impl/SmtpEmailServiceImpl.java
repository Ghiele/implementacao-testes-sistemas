package com.italoghiele.projetointegrador.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class SmtpEmailServiceImpl extends AbstractEmailService{

    @Autowired
    private MailSender mailSender;

    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailServiceImpl.class);

    @Override
    public void sendEmail(SimpleMailMessage simpleMailMessage) {
        LOG.info("Enviando email...");
        mailSender.send(simpleMailMessage);
        LOG.info("Email enviado");

    }

//    public void sendHtmlEmail(MimeMessage simpleMailMessage) {
//        LOG.info("Enviando email...");
//        javaMailSender.send(simpleMailMessage);
//        LOG.info("Email enviado");
//    }
}
