package com.poly.Lab6.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.Lab6.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    // Additional query methods (if needed) can be added here
}
