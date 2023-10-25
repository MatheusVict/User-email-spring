package io.matheusvictor.email.microservice.dtos;

import java.util.UUID;

public record EmailRecordDTO(
        UUID userID,
        String emailTo,
        String subject,
        String body
) {
}
