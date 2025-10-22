package com.poly.Lab8.service;

import com.poly.Lab8.entity.Account;

public interface AccountService {
    Account findById(String username);
}