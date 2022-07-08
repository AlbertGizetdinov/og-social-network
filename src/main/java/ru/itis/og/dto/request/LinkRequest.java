package ru.itis.og.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.itis.og.validation.annotation.Uri;
import ru.itis.og.validation.annotation.Uuid;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LinkRequest {

    @Uuid
    private String accountId;

    private String title;

    @NotBlank
    @Uri
    private String link;

}
