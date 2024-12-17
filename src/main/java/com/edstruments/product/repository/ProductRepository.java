package com.edstruments.product.repository;

import com.edstruments.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * author : Sagar Wagh
 */

/**
 * @Repository for Product Entity . (All JPA , Spring Data JPA operations will occur using this repository)
 */
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
