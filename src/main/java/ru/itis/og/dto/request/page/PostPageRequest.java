package ru.itis.og.dto.request.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.itis.og.validation.annotation.Uuid;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PostPageRequest extends PageableRequest {

    @Uuid
    private String accountId;

}
