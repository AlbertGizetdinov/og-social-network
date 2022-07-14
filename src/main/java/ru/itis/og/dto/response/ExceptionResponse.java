package ru.itis.og.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
@Builder
public class ExceptionResponse {
    @Builder.Default
    private final long timestamp = Instant.now().getEpochSecond();
    private int code;
    private HttpStatus status;
    private String message;
}
