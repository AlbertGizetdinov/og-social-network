package ru.itis.og.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.itis.og.validation.annotation.Uuid;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {

    @Uuid
    private String id;

    @Size(min = 5, max = 50, message = "Min title size is {min}, max title size is {max}")
    private String title;

    @Size(max = 1000, message = "Max text size is {max}")
    private String text;

    @Uuid
    private String account_id;

}
