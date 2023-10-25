package io.matheusvictor.email.microservice.consumer;

import io.matheusvictor.email.microservice.domain.Email;
import io.matheusvictor.email.microservice.dtos.EmailRecordDTO;
import io.matheusvictor.email.microservice.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class emailConsumer {

    final EmailService emailService;

    public emailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void listenEmailQueue(@Payload EmailRecordDTO emailRecordDTO) {
        Email email = new Email();
        BeanUtils.copyProperties(emailRecordDTO, email);
        this.emailService.sendEmail(email);
    }
}
