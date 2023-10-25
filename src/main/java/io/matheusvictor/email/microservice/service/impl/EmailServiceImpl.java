package io.matheusvictor.email.microservice.service.impl;

import io.matheusvictor.email.microservice.domain.Email;
import io.matheusvictor.email.microservice.enums.StatusEmail;
import io.matheusvictor.email.microservice.repository.EmailRepository;
import io.matheusvictor.email.microservice.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class EmailServiceImpl implements EmailService {
    final EmailRepository emailRepository;
    final JavaMailSender emailSender;

    public EmailServiceImpl(EmailRepository emailRepository, JavaMailSender emailSender) {
        this.emailRepository = emailRepository;
        this.emailSender = emailSender;
    }

    @Value("${spring.mail.username}")
    private String emailFrom;


    @Override
    @Transactional
    public Email sendEmail(Email email) {
        try {
            email.setSendEmailAt(LocalDateTime.now());
            email.setEmailFrom(emailFrom);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getBody());
            emailSender.send(message);

            email.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e) {
            email.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return emailRepository.save(email);
        }
    }
}
