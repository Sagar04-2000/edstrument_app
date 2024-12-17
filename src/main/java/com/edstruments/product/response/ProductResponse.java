package com.edstruments.product.response;

import com.edstruments.product.dto.ProductDTO;

public class ProductResponse extends Response{

    ProductDTO productDTO;


    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }
}
