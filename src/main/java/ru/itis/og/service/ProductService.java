package ru.itis.og.service;

import ru.itis.og.dto.request.ProductRequest;
import ru.itis.og.dto.request.page.ProductPageRequest;
import ru.itis.og.dto.response.ProductResponse;
import ru.itis.og.dto.response.page.ProductPageResponse;

public interface ProductService {

    ProductPageResponse getProducts(ProductPageRequest productPageRequest);

    ProductResponse createProduct(ProductRequest productRequest);

    ProductResponse updateProduct(ProductRequest productRequest);

    void deleteProduct(ProductRequest productRequest);

}
