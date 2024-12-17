package com.edstruments.product.service;

import com.edstruments.product.request.ProductRequest;
import com.edstruments.product.response.ProductListResponse;
import com.edstruments.product.response.ProductResponse;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<ProductResponse> createProduct(ProductRequest productDTO);

    ResponseEntity<ProductResponse> getProductByID(Long id);

    ResponseEntity<ProductListResponse> getAllProducst();

    ResponseEntity<ProductResponse> updateProductByID(Long id, ProductRequest productRequest);

    ResponseEntity<ProductResponse> deleteProductByID(Long id);
}
