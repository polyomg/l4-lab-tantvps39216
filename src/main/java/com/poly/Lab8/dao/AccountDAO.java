package com.poly.Lab8.dao;

import com.poly.Lab8.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDAO extends JpaRepository<Account, String> {
}