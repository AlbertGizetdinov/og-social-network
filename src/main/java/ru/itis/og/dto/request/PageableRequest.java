package ru.itis.og.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Min;

import static ru.itis.og.constant.OgConstant.PAGEABLE_DEFAULT_SIZE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PageableRequest {

    @Min(value = 0, message = "Min value is {value}")
    private int page = 0;

    @Min(value = 1, message = "Min value is {value}")
    private int size = PAGEABLE_DEFAULT_SIZE;

}
