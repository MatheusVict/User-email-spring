package io.matheusvictor.email.microservice.service;

import io.matheusvictor.email.microservice.domain.Email;

public interface EmailService {
    Email sendEmail(Email email);
}
