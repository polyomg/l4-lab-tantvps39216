package com.poly.Lab5_PS39216.Service;

import java.util.Collection;

import com.poly.Lab5_PS39216.entitys.Item;


public interface ShoppingCartService {
    Item add(Integer id);
    void remove(Integer id);
    Item update(Integer id, int qty);
    void clear();
    Collection<Item> getItems();
    int getCount();
    double getAmount();
}
