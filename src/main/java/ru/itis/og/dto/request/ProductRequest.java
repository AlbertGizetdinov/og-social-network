package ru.itis.og.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.itis.og.validation.annotation.Uuid;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @Uuid
    private String id;

    @Size(min = 5, max = 50, message = "Min name size is {min}, max name size is {max}")
    private String name;

    @Size(max = 1000, message = "Max description size is {max}")
    private String description;

    @NotNull
    private Float price;

    @Uuid
    private String accountId;
}
