package com.edstruments.product.response;

import com.edstruments.product.dto.ProductDTO;

import java.util.List;

public class ProductListResponse extends Response{

    List<ProductDTO> productDTOList;

    public List<ProductDTO> getProductDTOList() {
        return productDTOList;
    }

    public void setProductDTOList(List<ProductDTO> productDTOList) {
        this.productDTOList = productDTOList;
    }
}
