package com.springboot.shoppy_fullstack_app.repository;

import com.springboot.shoppy_fullstack_app.dto.CartItemDto;
import com.springboot.shoppy_fullstack_app.dto.CartListResponseDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class JdbcTemplateCartRepository implements CartRepository {
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateCartRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int add(CartItemDto cartItem) {
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
    public CartItemDto checkQty(CartItemDto cartItem) {
        String sql = """
                select ifnull(MAX(cid), 0) as cid,
                    count(*) as checkQty
                from cart
                where pid = ? and size = ? and id = ?
                """;
        Object[] params = {cartItem.getPid(), cartItem.getSize(), cartItem.getId()};
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(CartItemDto.class), params);
    }

    @Override
    public int updateQty(CartItemDto cartItem) {
        String sql = "";
        if(cartItem.getType().equals("+")) {
            sql = "update cart set qty = qty + 1 where cid = ?";
        } else {
            sql = "update cart set qty = qty - 1 where cid = ?";
        }
        return jdbcTemplate.update(sql, cartItem.getCid());
    }

    @Override
    public CartItemDto getCount(CartItemDto cartItem) {
        String sql = "select ifnull(sum(qty), 0) as sumQty from cart where id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(CartItemDto.class), cartItem.getId());
    }

    @Override
    public List<CartListResponseDto> findList(CartItemDto cartItem) {
        String sql = """
                select id, mname, phone, email, pid, name, info, image, price, size, qty, cid, totalPrice
                from view_cartlist
                where id = ?
                """;
        Object[] params = {cartItem.getId()};
        //mysql에서 결과값이 한줄이면 queryForObject / 한줄 이상, 여러줄이 나오면 query
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CartListResponseDto.class), params);
    }

    @Override
    public int deleteItem(CartItemDto cartItem) {
        String sql = """
                delete from cart where cid =?
                """;
        return jdbcTemplate.update(sql, cartItem.getCid());
    }
}
