package com.springboot.shoppy_fullstack_app.repository;

import com.springboot.shoppy_fullstack_app.dto.CartItem;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class JdbcTemplateCartRepository implements CartRepository {
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateCartRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int add(CartItem cartItem) {
        //DB 연동
        String sql = """
                insert into cart(size, qty, pid, id, cdate)
                    values(?, ?, ?, ?, now())
                """;
        Object [] params = {
                cartItem.getSize()
                , cartItem.getQty()
                , cartItem.getPid()
                , cartItem.getId()
        };
        return jdbcTemplate.update(sql, params);
    }

    @Override
    public CartItem checkQty(CartItem cartItem) {
        String sql = """
                select cid, sum(pid=? and size=? and id=?) as checkQty
                	from cart
                	group by cid, id
                	order by checkQty desc
                	limit 1;
                """;
        Object[] params = {cartItem.getPid(), cartItem.getSize(), cartItem.getId()};
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(CartItem.class), params);
    }

    @Override
    public int updateQty(CartItem cartItem) {
        String sql = "";
        if(cartItem.getType().equals("+")) {
            sql = "update cart set qty = qty + 1 where cid = ?";
        } else {
            sql = "update cart set qty = qty - 1 where cid = ?";
        }
        return jdbcTemplate.update(sql, cartItem.getCid());
    }
}
