package com.my.util;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {
    private final MailSender mailSender;

    public EmailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendSimpleEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("fhdhdhhshsfbfhdjejnd@gmail.com");
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
