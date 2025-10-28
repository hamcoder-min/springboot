package com.springboot.shoppy_fullstack_app.service;

import com.springboot.shoppy_fullstack_app.dto.KakaoPay;
import com.springboot.shoppy_fullstack_app.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//@Transactional
public class OrderServiceImpl implements OrderService{
    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl (OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @Override
    public int save(KakaoPay kakaoPay) {
        int rows = orderRepository.saveOrders(kakaoPay);
        if(rows ==1) {
            orderRepository.saveOrderDetail(kakaoPay);
        }
        return rows;
    }
}
