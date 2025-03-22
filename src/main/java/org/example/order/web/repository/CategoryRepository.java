package org.example.order.web.repository;

import org.example.order.web.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByDeletedFalse();
}
