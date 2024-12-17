package com.edstruments.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

/**
 * author : Sagar Wagh
 */

/**
 * This class will act as  Global Exception Handler responsible for catching exception and to send response.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    //This ExceptionHandler will be invoked when Product with particular Product ID not available in system
    // It will throw HttpStatus -404 ( Not Found ) with exception message and error details.
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> handleProductNotFoundException(ProductNotFoundException ex){
        ErrorDetails errorDetails=new ErrorDetails(new Date(),ex.getMessage(),ex.getLocalizedMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    //This ExceptionHandler will be invoked when invalid data is pass to the request such as productName,price
    // It will throw HttpStatus- 400 ( BAD REQUEST ) with exception message and error details.
    @ExceptionHandler(InvalidInputDataException.class)
    public ResponseEntity<?> handleInvalidInputDataException(InvalidInputDataException ex){
        ErrorDetails errorDetails=new ErrorDetails(new Date(),ex.getMessage(),ex.getExceptionList());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
