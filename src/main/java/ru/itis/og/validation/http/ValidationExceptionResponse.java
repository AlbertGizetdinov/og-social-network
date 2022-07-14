package ru.itis.og.validation.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidationExceptionResponse {

    @Builder.Default
    private final long timestamp = Instant.now().getEpochSecond();

    @Builder.Default
    private final int status = HttpStatus.BAD_REQUEST.value();

    private List<ValidationErrorDto> errors;
}

