import { createSlice } from '@reduxjs/toolkit';
import { act } from 'react';

const initialState = {
    productList: [],    //출력용 - 2차원 배열
    products: [],    //원본 - 1차원 배열
    product: {},
    imgList: []
}

export const productSlice = createSlice({
    name: 'product',
    initialState,
    reducers: {
        createProduct(state, action) {
            const {productList, products} = action.payload;
            state.productList = productList;
            state.products = products;
        },
        filterProduct(state, action) {
            // const pid = action.payload.pid;
            const {product} = action.payload;
            state.product = product;
            state.imgList = JSON.parse(product.imgList);

           //1. productList가 2차원 배열이므로 flat() 함수를 이용하여 1차원으로 변경 후 filter
            // const [filterProduct] = state.productList.flat().filter((item) => item.pid === pid);
            // state.product = filterProduct;

            //2. products 1차원 배열에서 find 함수
//            state.product = state.products.find(item => item.pid === pid);
        }
    },
})

// Action creators are generated for each case reducer function
export const { createProduct, filterProduct } = productSlice.actions //컴포넌트 또는 API 함수에서 dispatch(액션함수)

export default productSlice.reducer //store에서 호출(import)