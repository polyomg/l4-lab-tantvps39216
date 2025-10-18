package com.poly.Lab5_PS39216.Impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.poly.Lab5_PS39216.entitys.DB;
import com.poly.Lab5_PS39216.entitys.Item;
import com.poly.Lab5_PS39216.Service.ShoppingCartService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final Map<Integer, Item> cart = new HashMap<>();

    @Override
    public Item add(Integer id) {
        // Lấy sản phẩm từ DB.items
        Item item = DB.items.get(id);
        if (item == null) {
            return null; // Không tìm thấy sản phẩm trong DB
        }

        // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
        Item cartItem = cart.get(id);
        if (cartItem == null) {
            // Thêm sản phẩm mới vào giỏ hàng với số lượng 1
            cartItem = new Item(item.getId(), item.getName(), item.getPrice(), 1);
            cart.put(id, cartItem);
        } else {
            // Tăng số lượng lên 1
            cartItem.setQty(cartItem.getQty() + 1);
        }

        return cartItem;
    }

    @Override
    public void remove(Integer id) {
        cart.remove(id);
    }

    @Override
    public Item update(Integer id, int qty) {
        Item item = cart.get(id);
        if (item != null) {
            if (qty > 0) {
                item.setQty(qty);
            } else {
                cart.remove(id);
            }
        }
        return item;
    }

    @Override
    public void clear() {
        cart.clear();
    }

    @Override
    public Collection<Item> getItems() {
        return cart.values();
    }

    @Override
    public int getCount() {
        return cart.values().stream().mapToInt(Item::getQty).sum();
    }

    @Override
    public double getAmount() {
        return cart.values().stream().mapToDouble(item -> item.getPrice() * item.getQty()).sum();
    }
}