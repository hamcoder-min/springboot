package com.springboot.shoppy_fullstack_app.repository;

import com.springboot.shoppy_fullstack_app.dto.KakaoPay;

public interface OrderRepository {
    int saveOrders (KakaoPay kakaoPay);
    int saveOrderDetail(KakaoPay kakaoPay);
}
