package com.springboot.shoppy_fullstack_app.controller;

import com.springboot.shoppy_fullstack_app.dto.CartItemDto;
import com.springboot.shoppy_fullstack_app.dto.CartItemRequestDto;
import com.springboot.shoppy_fullstack_app.dto.CartItemResponseDto;
import com.springboot.shoppy_fullstack_app.dto.CartListResponseDto;
import com.springboot.shoppy_fullstack_app.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public int add(@RequestBody CartItemRequestDto requestDto) {
        return cartService.add(requestDto);
    }

    @PostMapping("/checkQty")
    public CartItemResponseDto checkQty(@RequestBody CartItemRequestDto requestDto) {
        return cartService.checkQty(requestDto);
    }

    @PostMapping("/updateQty")
    public int updateQty(@RequestBody CartItemRequestDto requestDto) {
        return cartService.updateQty(requestDto);
    }

    @PostMapping("/count")
    public CartItemResponseDto count(@RequestBody CartItemRequestDto requestDto) {
        return cartService.getCount(requestDto);
    }

    @PostMapping("/list")
    public List<CartListResponseDto> findList(@RequestBody CartItemRequestDto requestDto,
                                              HttpServletRequest request) {
        return cartService.findList(requestDto);
    }

    @PostMapping("/deleteItem")
    public int deleteItem(@RequestBody CartItemRequestDto requestDto) {
        return cartService.deleteItem(requestDto);
    }
}
