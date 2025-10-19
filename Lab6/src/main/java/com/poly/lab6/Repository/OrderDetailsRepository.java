package com.poly.Lab6.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.Lab6.Entity.OrderDetail;


public interface OrderDetailsRepository extends JpaRepository<OrderDetail, Integer> {
    // Additional query methods (if needed) can be added here
}
