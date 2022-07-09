package ru.itis.og.dto.response.page;

import lombok.*;
import ru.itis.og.dto.response.ProductResponse;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductPageResponse {

    List<ProductResponse> products;
    int totalPages;

}
