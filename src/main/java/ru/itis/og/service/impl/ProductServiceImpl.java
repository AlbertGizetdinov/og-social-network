package ru.itis.og.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.itis.og.dto.request.IdPageRequest;
import ru.itis.og.dto.request.ProductRequest;
import ru.itis.og.dto.response.ProductResponse;
import ru.itis.og.dto.response.page.ProductPageResponse;
import ru.itis.og.exception.AccountNotFoundException;
import ru.itis.og.exception.ProductNotFoundException;
import ru.itis.og.model.Product;
import ru.itis.og.model.enumeration.State;
import ru.itis.og.repository.AccountRepository;
import ru.itis.og.repository.ProductRepository;
import ru.itis.og.service.ProductService;

import java.util.UUID;

import static ru.itis.og.constant.OgConstant.DEFAULT_STATE;
import static ru.itis.og.dto.response.ProductResponse.from;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final AccountRepository accountRepository;

    @Override
    public ProductPageResponse getProducts(IdPageRequest idPageRequest) {
        PageRequest pageRequest = PageRequest.of(idPageRequest.getPage(), idPageRequest.getSize(), Sort.by("createDate"));
        Page<Product> productPage = productRepository.findAllByAccount_IdAndState(UUID.fromString(idPageRequest.getId()),
                DEFAULT_STATE, pageRequest);
        return ProductPageResponse.builder()
                .products(from(productPage.getContent()))
                .totalPages(productPage.getTotalPages())
                .build();
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .state(State.PUBLISHED)
                .account(accountRepository.findById(UUID.fromString(productRequest.getAccountId()))
                        .orElseThrow(AccountNotFoundException::new))
                .build();
        return from(productRepository.save(product));
    }

    @Override
    public ProductResponse updateProduct(ProductRequest productRequest) {
        Product product = productRepository.findById(UUID.fromString(productRequest.getId()))
                .orElseThrow(ProductNotFoundException::new);
        if (productRequest.getName() != null) {
            product.setName(productRequest.getName());
        }
        if (productRequest.getDescription() != null) {
            product.setDescription(productRequest.getDescription());
        }
        if (productRequest.getPrice() != null) {
            product.setPrice(productRequest.getPrice());
        }
        return from(productRepository.save(product));
    }

    @Override
    public void deleteProduct(ProductRequest productRequest) {
        Product product = productRepository.findById(UUID.fromString(productRequest.getId()))
                .orElseThrow(ProductNotFoundException::new);
        product.setState(State.DELETED);
        productRepository.save(product);
    }
}
