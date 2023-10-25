package io.matheusvictor.email.microservice.repository;

import io.matheusvictor.email.microservice.domain.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<Email, UUID> {
}
