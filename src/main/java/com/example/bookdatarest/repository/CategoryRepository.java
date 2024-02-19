package com.example.bookdatarest.repository;

import com.example.bookdatarest.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
