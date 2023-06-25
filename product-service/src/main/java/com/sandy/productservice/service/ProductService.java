package com.sandy.productservice.service;

import com.sandy.productservice.model.ProductRequest;
import com.sandy.productservice.model.Product;
import com.sandy.productservice.model.ProductResponse;
import com.sandy.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product is saved, {}",product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<ProductResponse> products =productRepository.findAll()
                .stream().map(product -> mapToProductResponse(product)).toList();
        return  products;
    }

    private ProductResponse mapToProductResponse(Product product) {
        return  ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
