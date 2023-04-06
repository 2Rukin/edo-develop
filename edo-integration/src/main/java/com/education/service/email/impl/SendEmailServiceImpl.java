package com.education.service.email.impl;

import com.education.properties.PropertyParameters;
import com.education.service.email.SendEmailService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @author Andrey Kryukov
 * Сервис отправляет письма
 */
@Component
@Log4j2
@AllArgsConstructor
public class SendEmailServiceImpl implements SendEmailService {

    private PropertyParameters property;
    private JavaMailSender mailSender;

    /**
     * Отправляет письмо с переданными параметрами
     * @param to
     * @param subject
     * @param text
     */
    @Override
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(property.getEmailUsername());
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        log.info("Message object is formed");
        mailSender.send(message);
        log.info("The email has been sent");
    }
}
