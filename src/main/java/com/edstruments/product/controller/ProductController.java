package com.edstruments.product.controller;

import com.edstruments.product.request.ProductRequest;
import com.edstruments.product.response.ProductListResponse;
import com.edstruments.product.response.ProductResponse;
import com.edstruments.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * author : Sagar Wagh
 *
 **/
/**
 * This ProductController class is responsible for handling all product related requests from Client
 */

@RestController
@RequestMapping("/api/products")
public class ProductController {


    //Creating logger instance for logging purpose.
    private static final Logger logger= LoggerFactory.getLogger("PRODUCT_CONTROLLER");

    //Injecting Dependency using @Autowired
    //Instead of @Autowired we can use  setter injection and constructor injection as well.
    @Autowired
    private ProductService productService;

    @PostMapping("")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest){

        logger.info("PRODUCT_CONTROLLER_STARTED >>>>>");
        logger.info("Request for creating a new product >>>>>");
        return productService.createProduct(productRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductByID(@PathVariable ("id") Long id){

        logger.info("PRODUCT_CONTROLLER_STARTED >>>>>");
        logger.info("Request for load a product by product Id >>>>>");
        return productService.getProductByID(id);
    }

    @GetMapping("")
    public ResponseEntity<ProductListResponse> getAllProducts(){

        logger.info("PRODUCT_CONTROLLER_STARTED >>>>>");
        logger.info("Request for load all products >>>>>");
        return productService.getAllProducst();
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductResponse> updateProductByID(@PathVariable ("id") Long id,@RequestBody ProductRequest productRequest){

        logger.info("PRODUCT_CONTROLLER_STARTED >>>>>");
        logger.info("Request for update a product by product Id >>>>>");
        return productService.updateProductByID(id,productRequest);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ProductResponse> deleteProductByID(@PathVariable ("id") Long id){

        logger.info("PRODUCT_CONTROLLER_STARTED >>>>>");
        logger.info("Request for deleting a product by product Id >>>>>");
        return productService.deleteProductByID(id);
    }
}

