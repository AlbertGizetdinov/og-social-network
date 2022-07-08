package ru.itis.og.converter;

import org.springframework.http.HttpStatus;
import ru.itis.og.exception.OgServiceException;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.net.URI;
import java.net.URISyntaxException;

@Converter(autoApply = true)
public class UriConverter implements AttributeConverter<URI, String> {

    @Override
    public String convertToDatabaseColumn(URI uri) {
        return (uri == null) ? null : uri.toString();
    }

    @Override
    public URI convertToEntityAttribute(String string) {
        try {
            return (string == null) ? null : new URI(string);
        } catch (URISyntaxException e) {
            throw new OgServiceException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
