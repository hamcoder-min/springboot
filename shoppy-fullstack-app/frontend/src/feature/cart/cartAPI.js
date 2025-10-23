import React from 'react';
import { addCartItem, updateCartCount, showCartItem, updateTotalPrice, updateCartItem, removeCartItem } from './cartSlice.js';
import { axiosData, axiosPost, axiosGet } from '../../utils/dataFetch.js';

//데이터 흐름 : cartAPI > cartSlice > store > Cart.jsx

/** 장바구니 카운트 */
export const getCartCount = async (id) => {
    const url = "/cart/count";
    const data = {"id": id}
    const jsonData = await axiosPost(url, data);
    return jsonData.sumQty;
}

export const removeCart = (cid) => async (dispatch) => {
    dispatch(removeCartItem({"cid": cid}));
    dispatch(updateTotalPrice());
    dispatch(updateCartCount());
}

export const showCart = () => async (dispatch) => {
//    const jsonData = await axiosData('/data/products.json');
    const url = "/cart/list";
    const {userId} = JSON.parse(localStorage.getItem("loginInfo"));
    const jsonData = await axiosPost(url, {"id": userId});
    console.log("jsonData --> ", jsonData);
    dispatch(showCartItem({"items": jsonData}));
//    dispatch(updateTotalPrice());
}

export const updateCart = async (cid, type) => {
    const url = "/cart/updateQty";
    const data = {"cid": cid, "type": type};
    const rows = await axiosPost(url, data);
    console.log('updateCart rows -> ', rows);
    return rows;
//    dispatch(updateCartItem({"cid": cid, "type": type}));   //cartList 수량 변경
//    dispatch(updateTotalPrice());
//    dispatch(updateCartCount());
}

export const checkQty = async (pid, size, id) => {
    //쇼핑백 추가한 상품과 사이즈가 DB 테이블에 있는지 유무 확인
    const url = "/cart/checkQty";
    const data = {"pid": pid, "size": size, "id": id};
    const jsonData = await axiosPost(url, data);
    return jsonData;
}

export const addCart = (pid, size) => async (dispatch) => {
    const {userId} = JSON.parse(localStorage.getItem("loginInfo"));
    const checkResult = await checkQty(pid, size, userId);

    if(!checkResult.checkQty) {
        const url = "/cart/add";
        const item = {"pid": pid, "size": size, "qty": 1, "id": userId};
        const rows = await axiosPost(url, item);
        alert("상품이 추가되었습니다.");
        dispatch(updateCartCount({"count": 1, "type": true}));
    } else {
        //qty update
        const rows = await updateCart(checkResult.cid, "+");
        dispatch(updateCartCount({"count": 1, "type": false}));
        alert("상품이 추가되었습니다.");
    }
    return 1;
}