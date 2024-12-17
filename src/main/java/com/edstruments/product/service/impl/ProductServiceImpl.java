package com.edstruments.product.service.impl;

import com.edstruments.product.dto.ProductDTO;
import com.edstruments.product.entity.Product;
import com.edstruments.product.exception.InvalidInputDataException;
import com.edstruments.product.exception.ProductNotFoundException;
import com.edstruments.product.repository.ProductRepository;
import com.edstruments.product.request.ProductRequest;
import com.edstruments.product.response.ProductListResponse;
import com.edstruments.product.response.ProductResponse;
import com.edstruments.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * author : Sagar Wagh
 */

/**
 * ProductServiceImpl  - All business logic related to Product will be written here.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger= LoggerFactory.getLogger("PRODUCT_SERVICE");

    //Injecting dependency of ProductRepository.
    @Autowired
    private ProductRepository productRepository;

    //This method is responsible for creating a new product.
    @Override
    public ResponseEntity<ProductResponse> createProduct(ProductRequest productRequest) {
        logger.info("PRODUCT_SERVICE_STARTED >>>>>");

        ResponseEntity<ProductResponse> response=null;
        ProductResponse productResponse =new ProductResponse();

        HttpStatus httpStatus=HttpStatus.OK;
        boolean hasExceptionOccured=false;
        List<String> exceptionMessage=null;

        productResponse.setMessage("Product created Successfully!");
        productResponse.setHttpStatusCode(httpStatus);

        try {
            if (productRequest != null) {

                exceptionMessage=validateProductRequest(productRequest);

                if(!exceptionMessage.isEmpty()){
                    logger.error("Invalid Input Data");
                    throw new InvalidInputDataException("Invalid Input Data");
                }

                Product product = new Product();
                product.setName(productRequest.getProductName());
                product.setDescription(productRequest.getDescription());
                product.setPrice(productRequest.getPrice());

                product=productRepository.save(product);

                ProductDTO productDTO=mapProductToProductDTO(product);
                productResponse.setProductDTO(productDTO);
            }else{
                logger.error("Invalid Input Data >>>> Product Request is NULL");
                throw new InvalidInputDataException("Invalid Input Data");
            }
        }catch(Exception e){
            hasExceptionOccured=true;
            //In this catch Block we are checking the instance type of exception
            // and based on that we are rethrowing the exception
            // This thrown exception will be managed by GlobalExceptionHandler
            if(e instanceof InvalidInputDataException)
                throw new InvalidInputDataException("Invalid Input Data",exceptionMessage);
        }finally {
            if(hasExceptionOccured){
                httpStatus=HttpStatus.INTERNAL_SERVER_ERROR;
                productResponse.setMessage("Exception in creating product");
                productResponse.setHttpStatusCode(httpStatus);
            }

            response=new ResponseEntity<>(productResponse,httpStatus);
        }

        return response;
    }

    //This method is responsible for validating input data like productName,productDescription
    // and based on this validation we are throwing InvalidInputDataException.
    private List<String> validateProductRequest(ProductRequest productRequest) {

        String productNameExceptionMessage="Name : Should not be empty";
        String productPriceExceptionMessage="Price : Should be a positive value";
        List<String> exceptionMessageList=new ArrayList<>();

        //to check if productName is NULL or EMPTY
        if(productRequest.getProductName()==null ||
                productRequest.getProductName().isEmpty()){
            exceptionMessageList.add(productNameExceptionMessage);
        }

        //to check if productPrice is < 0
        if(productRequest.getPrice()<0){
            exceptionMessageList.add(productPriceExceptionMessage);
        }

        return exceptionMessageList;

    }

    //This method is responsible for loading a product by its ID.
    @Override
    public ResponseEntity<ProductResponse> getProductByID(Long id) {
        logger.info("PRODUCT_SERVICE_STARTED >>>>>");

        ResponseEntity<ProductResponse> response=null;
        ProductResponse productResponse =new ProductResponse();

        HttpStatus httpStatus=HttpStatus.OK;
        boolean hasExceptionOccured=false;

        productResponse.setMessage("Product loaded Successfully!");
        productResponse.setHttpStatusCode(httpStatus);

        try{

            //Using Optional as a return type.
            Optional<Product> productObject=productRepository.findById(id);
            if(productObject.isPresent()){
                Product product=productObject.get();
                ProductDTO productDTO=new ProductDTO();

                productDTO.setProductId(product.getProductId());
                productDTO.setName(product.getName());
                productDTO.setDescription(product.getDescription());
                productDTO.setPrice(product.getPrice());

                productResponse.setProductDTO(productDTO);
            }else{
                //throwing ProductNotFoundException when Product with payload ID is not available in system
                logger.error("Product Not found with ID: "+id);
                throw new ProductNotFoundException("Product Not Found with ID: "+id);
                //productResponse.setMessage("Product with (ID = "+id+" ) not present in system");
            }

        }catch(Exception e){
            hasExceptionOccured=true;
            if( e instanceof ProductNotFoundException)
                throw new ProductNotFoundException(e.getMessage());
        }finally{
            if(hasExceptionOccured){
                httpStatus=HttpStatus.INTERNAL_SERVER_ERROR;
                productResponse.setMessage("Exception in loading product");
                productResponse.setHttpStatusCode(httpStatus);
            }

            response=new ResponseEntity<>(productResponse,httpStatus);
        }

        return response;
    }

    //This method is responsible for loading all products.
    @Override
    public ResponseEntity<ProductListResponse> getAllProducst() {

        logger.info("PRODUCT_SERVICE_STARTED >>>>>");

        ResponseEntity<ProductListResponse> response=null;
        ProductListResponse productListResponse =new ProductListResponse();

        HttpStatus httpStatus=HttpStatus.OK;
        boolean hasExceptionOccured=false;

        productListResponse.setMessage("Product loaded Successfully!");
        productListResponse.setHttpStatusCode(httpStatus);

        try{

            List<Product> productList  = productRepository.findAll();
            List<ProductDTO> productDTOList=new ArrayList<>();

            productDTOList=productList.stream().map(this::mapProductToProductDTO).toList();

            productListResponse.setProductDTOList(productDTOList);

        }catch(Exception e){
            hasExceptionOccured=true;
        }finally {
            if(hasExceptionOccured){
                httpStatus=HttpStatus.INTERNAL_SERVER_ERROR;
                productListResponse.setMessage("Exception in loading product");
                productListResponse.setHttpStatusCode(httpStatus);
            }

            response=new ResponseEntity<>(productListResponse,httpStatus);
        }

        return response;
    }


    //This Method is responsible for updating a product by passing ID and product Metadata.
    @Override
    public ResponseEntity<ProductResponse> updateProductByID(Long id, ProductRequest productRequest) {
        logger.info("PRODUCT_SERVICE_STARTED >>>>>");

        ResponseEntity<ProductResponse> response=null;
        ProductResponse productResponse =new ProductResponse();

        HttpStatus httpStatus=HttpStatus.OK;
        boolean hasExceptionOccured=false;

        productResponse.setMessage("Product updated Successfully!");
        productResponse.setHttpStatusCode(httpStatus);
        List<String> exceptionMessage=null;
        try{

            Optional<Product> productObject=productRepository.findById(id);
            if(productObject.isPresent()){


                exceptionMessage=validateProductRequest(productRequest);

                if(!exceptionMessage.isEmpty()){
                    throw new InvalidInputDataException("Invalid Input Data");
                }

                Product product=productObject.get();
                product.setName(productRequest.getProductName());
                product.setDescription(productRequest.getDescription());
                product.setPrice(productRequest.getPrice());
                product=productRepository.save(product);

                ProductDTO productDTO=this.mapProductToProductDTO(product);


                productResponse.setProductDTO(productDTO);
            }else{
                //throwing ProductNotFoundException when Product with payload ID is not available in system
                logger.error("Product Not found with ID: "+id);
                throw new ProductNotFoundException("Product Not Found with ID: "+id);
            }

        }catch(Exception e){
            hasExceptionOccured=true;

            //In this catch Block we are checking the instance type of exception
            // and based on that we are rethrowing the exception
            // This thrown exception will be managed by GlobalExceptionHandler
            if( e instanceof ProductNotFoundException)
                throw new ProductNotFoundException(e.getMessage());
            if(e instanceof InvalidInputDataException)
                throw new InvalidInputDataException("Invalid Input Data",exceptionMessage);

        }finally{
            if(hasExceptionOccured){
                httpStatus=HttpStatus.INTERNAL_SERVER_ERROR;
                productResponse.setMessage("Exception in loading product");
                productResponse.setHttpStatusCode(httpStatus);
            }

            response=new ResponseEntity<>(productResponse,httpStatus);
        }

        return response;
    }

    //This Method is responsible for deleting a Product By ID.
    @Override
    public ResponseEntity<ProductResponse> deleteProductByID(Long id) {

        logger.info("PRODUCT_SERVICE_STARTED >>>>>");

        ResponseEntity<ProductResponse> response=null;
        ProductResponse productResponse =new ProductResponse();

        HttpStatus httpStatus=HttpStatus.OK;
        boolean hasExceptionOccured=false;

        productResponse.setMessage("Product deleted Successfully!");
        productResponse.setHttpStatusCode(httpStatus);

        try{

            Optional<Product> productObject=productRepository.findById(id);
            if(productObject.isPresent()){

                productRepository.delete(productObject.get());
            }else{
                //throwing ProductNotFoundException when Product with payload ID is not available in system
                logger.error("Product Not found with ID: "+id);
                throw new ProductNotFoundException("Product Not Found with ID: "+id);
            }

        }catch(Exception e){
            hasExceptionOccured=true;
            if( e instanceof ProductNotFoundException)
                throw new ProductNotFoundException(e.getMessage());
        }finally {
            if(hasExceptionOccured){
                httpStatus=HttpStatus.INTERNAL_SERVER_ERROR;
                productResponse.setMessage("Exception in loading product");
                productResponse.setHttpStatusCode(httpStatus);
            }

            response=new ResponseEntity<>(productResponse,httpStatus);
        }
        return response;
    }

    //This Method is responsible for mapping Product to ProductDTO
    private ProductDTO mapProductToProductDTO(Product product) {

        ProductDTO productDTO=new ProductDTO();
        productDTO.setProductId(product.getProductId());
        if(product.getName()!=null)
         productDTO.setName(product.getName());
        if(product.getDescription()!=null)
         productDTO.setDescription(product.getDescription());
        if(product.getPrice()!=null)
          productDTO.setPrice(product.getPrice());

        return productDTO;
    }
}
