package com.poly.Lab8.service;

import com.poly.Lab8.dao.AccountDAO;
import com.poly.Lab8.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDAO accountDAO;

    @Override
    public Account findById(String username) {
        // Dùng orElse(null) để trả về null nếu không tìm thấy
        return accountDAO.findById(username).orElse(null);
    }
}