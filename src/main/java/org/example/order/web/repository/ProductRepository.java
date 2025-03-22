package org.example.order.web.repository;

import org.example.order.web.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByDeletedFalse();
}
