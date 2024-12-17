package com.edstruments.product.entity;

import jakarta.persistence.*;
import lombok.NonNull;

/**
 *
 * author : Sagar Wagh
 */

/**
 * This Entity Class is responsible for creating entity , additionally I have used Spring Data JPA
 * to create database table specific to entity.
 */
@Entity
@Table(name="Product")
public class Product {

    private static final long serialVersionUID=1L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="price")
    private Double price;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
