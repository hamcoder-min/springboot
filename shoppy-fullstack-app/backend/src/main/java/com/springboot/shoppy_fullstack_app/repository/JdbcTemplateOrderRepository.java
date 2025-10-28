package com.springboot.shoppy_fullstack_app.repository;

import com.springboot.shoppy_fullstack_app.dto.KakaoPay;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class JdbcTemplateOrderRepository implements OrderRepository{
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateOrderRepository() {
    }
    @Override
    public int saveOrders(KakaoPay kakaoPay) {
        String sql = """
                insert into orders(order_code, member_id, shipping_fee, discount_amount, total_amount, receiver_name, receiver_phone, zipcode, address1, address2, memo, odate)
                values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now())
                """;
        Object[] params = {
            kakaoPay.getOrderId(),
                
        };
        return 0;
    }

    @Override
    public int saveOrderDetail(KakaoPay kakaoPay) {
        String sql = """
                """;
        return 0;
    }
}
