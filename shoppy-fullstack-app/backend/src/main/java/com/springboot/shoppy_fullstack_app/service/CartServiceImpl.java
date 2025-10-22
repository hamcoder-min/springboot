package com.springboot.shoppy_fullstack_app.service;

import com.springboot.shoppy_fullstack_app.dto.CartItem;
import com.springboot.shoppy_fullstack_app.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    private CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public int add(CartItem cartItem) {
        return cartRepository.add(cartItem);
    }

    @Override
    public String checkQty(CartItem cartItem) {
        CartItem result = cartRepository.checkQty(cartItem);
        System.out.println("checkQty -> " + result.getCheckQty());
        System.out.println("cid -> " + result.getCid());
        return "";
    }
}
