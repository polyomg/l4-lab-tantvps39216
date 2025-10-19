package com.poly.Lab6.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.Lab6.Entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
