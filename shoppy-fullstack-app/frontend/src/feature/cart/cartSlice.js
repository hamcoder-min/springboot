import { createSlice } from '@reduxjs/toolkit'
import { cartItemsAddInfo, cartItemsCheck } from '../../utils/cart.js';

const initialState = {
    cartCount: 0,
    cartList: [],
    totalPrice: 0
}

export const cartSlice = createSlice({
    name: 'cart',
    initialState,
    reducers: {
        addCartItem(state, action) {
            const {cartItem} = action.payload;
            state.cartList = cartItemsCheck(state.cartList, cartItem);
        },
        showCartItem(state, action) {
            const {items} = action.payload;
            state.cartList = items;
//            state.cartList = cartItemsAddInfo(items, state.cartList);
        },
        updateCartCount(state, action) {
//            state.cartCount = state.cartList.reduce((total, item) => total + item.qty, 0);
            const {count, type} = action.payload;
            type ?
                state.cartCount += action.payload.count
            :
                state.cartCount = count;
        },
        updateTotalPrice(state) {
            state.totalPrice = state.cartList.reduce((total, item) => total + (item.price * item.qty), 0);
        },
        updateCartItem(state, action) {
            const {cid, type} = action.payload;
            state.cartList = state.cartList.map((item) => 
                item.cid === cid ?
                    type === '+'? 
                        {...item, qty: item.qty+1} 
                    :   item.qty > 1 ?
                            {...item, qty: item.qty-1} 
                        :   item
                :   item
            );
        },
        removeCartItem (state, action) {
            const {cid} = action.payload;
            state.cartList = state.cartList.filter(item => !(item.cid === cid));
        }
    },
})

// Action creators are generated for each case reducer function
export const { addCartItem, showCartItem, updateCartCount, updateTotalPrice, updateCartItem, removeCartItem } = cartSlice.actions //컴포넌트 또는 API 함수에서 dispatch(액션함수)

export default cartSlice.reducer //store에서 호출(import)