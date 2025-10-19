package com.poly.Lab7.dao;

import java.util.List;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.poly.Lab7.entity.Product;
import com.poly.Lab7.entity.Report;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {

    // Bài 1: JPQL
    @Query("FROM Product o WHERE o.price BETWEEN ?1 AND ?2")
    List<Product> findByPrice(double minPrice, double maxPrice);

    // Bài 2: JPQL + phân trang
    @Query("FROM Product o WHERE o.name LIKE ?1")
    Page<Product> findByKeywords(String keywords, Pageable pageable);

    // Bài 3: Tổng hợp dữ liệu
    @Query("SELECT o.category AS group, SUM(o.price) AS sum, COUNT(o) AS count "
            + "FROM Product o GROUP BY o.category ORDER BY SUM(o.price) DESC")
    List<Report> getInventoryByCategory();


    // Bài 4: DSL (tương tự bài 1)
    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    // Bài 5: DSL (tương tự bài 2)
    Page<Product> findAllByNameLike(String keywords, Pageable pageable);
}


