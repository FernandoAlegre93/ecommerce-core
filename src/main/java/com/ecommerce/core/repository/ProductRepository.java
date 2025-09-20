package com.ecommerce.core.repository;


import com.ecommerce.core.model.ProductEntity;
import com.ecommerce.core.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<UserEntity> findById(String productId);
}