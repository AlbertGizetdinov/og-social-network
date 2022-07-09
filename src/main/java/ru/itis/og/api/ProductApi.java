package ru.itis.og.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.og.dto.request.IdPageRequest;
import ru.itis.og.dto.request.ProductRequest;
import ru.itis.og.dto.response.ProductResponse;
import ru.itis.og.dto.response.page.ProductPageResponse;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import static ru.itis.og.constant.OgConstant.PRODUCT_CONTROLLER_PATH;

@RequestMapping(PRODUCT_CONTROLLER_PATH)
public interface ProductApi {

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    ResponseEntity<ProductPageResponse> getProducts(@RequestBody IdPageRequest idPageRequest);

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productRequest);

    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest productRequest);

    @DeleteMapping(consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<?> deleteProduct(@RequestBody ProductRequest productRequest);
}
