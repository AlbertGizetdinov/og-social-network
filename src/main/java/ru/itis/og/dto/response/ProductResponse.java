package ru.itis.og.dto.response;

import lombok.*;
import ru.itis.og.model.Product;
import ru.itis.og.model.enumeration.State;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private UUID id;
    private Instant createDate;
    private String name;
    private String description;
    private Float price;
    private State state;
    private UUID accountId;

    public static ProductResponse from(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .createDate(product.getCreateDate())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .state(product.getState())
                .accountId(product.getAccount().getId())
                .build();
    }

    public static List<ProductResponse> from(List<Product> products) {
        return products.stream().map(ProductResponse::from).collect(Collectors.toList());
    }

}
