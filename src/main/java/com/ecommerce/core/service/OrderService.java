package com.ecommerce.core.service;



import com.ecommerce.core.model.OrderDetailEntity;
import com.ecommerce.core.model.OrderEntity;
import com.ecommerce.core.model.ProductEntity;
import com.ecommerce.core.model.UserEntity;
import com.ecommerce.core.repository.OrderRepository;
import com.ecommerce.core.repository.ProductRepository;
import com.ecommerce.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Transactional // Esto asegura que toda la operación se realice de forma atómica
    public OrderEntity createOrder(Long userId, List<OrderDetailEntity> orderDetails) {
        // 1. Validar que el usuario exista
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        // 2. Crear el nuevo pedido
        OrderEntity newOrder = new OrderEntity();
        newOrder.setUser(user);
        newOrder.setDate(LocalDateTime.now());
        newOrder.setStatus("PENDING");
        newOrder.setTotal(0.0);

        // 3. Procesar los detalles del pedido y calcular el total
        double total = 0.0;
        for (OrderDetailEntity detail : orderDetails) {
            ProductEntity product = productRepository.findById(detail.getProductEntity().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found with id " + detail.getProductEntity().getId()));

            if (product.getStock() < detail.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product " + product.getName());
            }

            // Actualizar el stock
            product.setStock(product.getStock() - detail.getQuantity());
            productRepository.save(product);

            // Calcular el precio y asociar el detalle al pedido
            detail.setUnitPrice(product.getPrice());
            detail.setOrderEntity(newOrder);
            total += detail.getQuantity() * detail.getUnitPrice();
        }

        newOrder.setTotal(total);
        newOrder.setOrderDetailEntities(orderDetails);

        return orderRepository.save(newOrder);
    }

    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<OrderEntity> getOrderById(Long id) {
        return orderRepository.findById(id);
    }
}