package com.ecommerce.core.repository;

import com.ecommerce.core.model.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetailEntity, Long> {
    Optional<OrderDetailEntity> findByOrderEntity_Id(Long orderEntityId);
}