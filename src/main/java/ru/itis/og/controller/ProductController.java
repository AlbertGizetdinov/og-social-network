package ru.itis.og.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.og.api.ProductApi;
import ru.itis.og.dto.request.ProductRequest;
import ru.itis.og.dto.request.page.ProductPageRequest;
import ru.itis.og.dto.response.ProductResponse;
import ru.itis.og.dto.response.page.ProductPageResponse;
import ru.itis.og.service.ProductService;

@RestController
@RequiredArgsConstructor
public class ProductController implements ProductApi {

    private final ProductService productService;

    @Override
    public ResponseEntity<ProductPageResponse> getProducts(ProductPageRequest productPageRequest) {
        return ResponseEntity.ok(productService.getProducts(productPageRequest));
    }

    @Override
    public ResponseEntity<ProductResponse> addProduct(ProductRequest productRequest) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(productService.createProduct(productRequest));
    }

    @Override
    public ResponseEntity<ProductResponse> updateProduct(ProductRequest productRequest) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(productService.updateProduct(productRequest));
    }

    @Override
    public ResponseEntity<?> deleteProduct(ProductRequest productRequest) {
        productService.deleteProduct(productRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
