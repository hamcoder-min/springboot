package com.springboot.shoppy_fullstack_app.controller;

import com.springboot.shoppy_fullstack_app.dto.CartItem;
import com.springboot.shoppy_fullstack_app.dto.CartListResponse;
import com.springboot.shoppy_fullstack_app.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {
    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public int add(@RequestBody CartItem cartItem) {
        return cartService.add(cartItem);
    }

    @PostMapping("/checkQty")
    public CartItem checkQty(@RequestBody CartItem cartItem) {
        return cartService.checkQty(cartItem);
    }

    @PostMapping("updateQty")
    public int updateQty(@RequestBody CartItem cartItem) {
        return cartService.updateQty(cartItem);
    }

    @PostMapping("/count")
    public CartItem count(@RequestBody CartItem cartItem) {
        return cartService.getCount(cartItem);
    }

    @PostMapping("/list")
    public ResponseEntity<?> findList(@RequestBody CartItem cartItem,
                                   HttpServletRequest request) {
        HttpSession session = request.getSession(false); //기존 생성 가져오기
        String sid = (String)session.getAttribute("sid");
        String ssid = session.getId();
        ResponseEntity<?> response = null;

        if(ssid != null && sid != null) {  //로그인 회원
            System.out.println("ssid :: " + ssid + "sid :: " + sid);
            List<CartListResponse> list = cartService.findList(cartItem);
            response = ResponseEntity.ok(list);
        } else {
            response = ResponseEntity.ok(Map.of("result", false));
        }

        return response;
    }

    @PostMapping("/deleteItem")
    public int deleteItem(@RequestBody CartItem cartItem) {
        return cartService.deleteItem(cartItem);
    }
}
