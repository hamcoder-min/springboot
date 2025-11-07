package com.springboot.shoppy_fullstack_app.controller;

import com.springboot.shoppy_fullstack_app.dto.CartItemDto;
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
    public int add(@RequestBody CartItemDto cartItem) {
        return cartService.add(cartItem);
    }

    @PostMapping("/checkQty")
    public CartItemDto checkQty(@RequestBody CartItemDto cartItem) {
        return cartService.checkQty(cartItem);
    }

    @PostMapping("updateQty")
    public int updateQty(@RequestBody CartItemDto cartItem) {
        return cartService.updateQty(cartItem);
    }

    @PostMapping("/count")
    public CartItemDto count(@RequestBody CartItemDto cartItem) {
        return cartService.getCount(cartItem);
    }

    @PostMapping("/list")
    public List<CartListResponseDto> findList(@RequestBody CartItemDto cartItem,
                                              HttpServletRequest request) {
//        HttpSession session = request.getSession(false); //기존 생성 가져오기
//        String sid = (String)session.getAttribute("sid");
//        String ssid = session.getId();
//        ResponseEntity<?> response = null;
//
//        if(ssid != null && sid != null) {  //로그인 회원
//            System.out.println("ssid :: " + ssid + "sid :: " + sid);
//            List<CartListResponse> list = cartService.findList(cartItem);
//            response = ResponseEntity.ok(list);
//        } else {
//            response = ResponseEntity.ok(Map.of("result", false));
//        }

        return cartService.findList(cartItem);
    }

    @PostMapping("/deleteItem")
    public int deleteItem(@RequestBody CartItemDto cartItem) {
        return cartService.deleteItem(cartItem);
    }
}
