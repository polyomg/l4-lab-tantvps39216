package com.poly.Lab6.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.Lab6.Entity.Category;
import com.poly.Lab6.Entity.Product;
import com.poly.Lab6.Entity.User;

public interface  ProductRepository extends JpaRepository<Product, Long>{
    Page<Product> findAll(Pageable pageable);
}
